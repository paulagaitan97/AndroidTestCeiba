package com.gaitan.dev.post_presentation.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaitan.dev.core.R
import com.gaitan.dev.core.utils.Constants
import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.core_ui.utils.UiEvent
import com.gaitan.dev.core_ui.utils.UiText
import com.gaitan.dev.post_domain.model.User
import com.gaitan.dev.post_domain.model.UserPostModel
import com.gaitan.dev.post_domain.usecase.AllCasesOfPost
import com.gaitan.dev.post_domain.usecase.GetPostsByUserCase
import com.gaitan.dev.post_presentation.transactions.PostViewState
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsCases: AllCasesOfPost,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(PostViewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        savedStateHandle.get<String>(Constants.USER_ID)?.let { id ->
            getPostByUser(id.toInt())

        }
    }

    private fun getPostByUser(
        userId: Int
    ) {
        viewModelScope.launch {
            state = state.copy(
                postsByUser = UserPostModel(
                    user = User(0, "", "", "", "", ""),
                    posts = emptyList()
                )
            )
            postsCases.getPostsByUserCase(userId)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { postsUser ->
                                state = state.copy(postsByUser = postsUser)
                            }

                        }
                        is Resource.Fail -> {
                            state = state.copy(postsByUser = UserPostModel(
                                user = User(0, "", "", "", "", ""),
                                posts = emptyList()
                            ))
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.text_error)
                                )
                            )
                        }
                    }

                }
        }
    }
}