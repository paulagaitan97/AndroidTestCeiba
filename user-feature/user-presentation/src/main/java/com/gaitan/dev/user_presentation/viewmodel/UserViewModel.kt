package com.gaitan.dev.user_presentation.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaitan.dev.core.R
import com.gaitan.dev.core.utils.Resource
import com.gaitan.dev.core_ui.utils.UiEvent
import com.gaitan.dev.core_ui.utils.UiText
import com.gaitan.dev.user_domain.usecase.AllCasesOfUser
import com.gaitan.dev.user_presentation.trasactions.UserEvent
import com.gaitan.dev.user_presentation.trasactions.UserViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val usersCases: AllCasesOfUser) : ViewModel() {
    var state by mutableStateOf(UserViewState())
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getUsers()
    }

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.OnGetUsers -> {
                getUsers()
            }
            is UserEvent.OnSearchTextChange -> {
                state = state.copy(searchText = event.query)
                searchUserByName()
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            state = state.copy(
                isGetUserService = false,
                getUsers = emptyList()
            )
            usersCases.getUsersCase()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { users ->
                                state = state.copy(isGetUserService = true, getUsers = users)
                            }

                        }
                        is Resource.Fail -> {
                            state = state.copy(getUsers = emptyList())
                            _uiEvent.send(
                                UiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.text_error_network)
                                )
                            )
                        }
                    }

                }
        }
    }

    private fun searchUserByName(
        searchText : String = state.searchText.lowercase()
    ) {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                getUsers = emptyList()
            )
            usersCases.getUsersByNameCase(searchText)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { users ->
                                state = if(users.isEmpty()){
                                    state.copy(getUsers = emptyList())
                                }else{
                                    state.copy(getUsers = users)
                                }
                            }

                        }
                        is Resource.Fail -> {
                            state = state.copy(getUsers = emptyList())
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