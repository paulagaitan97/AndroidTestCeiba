package com.gaitan.dev.post_presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gaitan.dev.core.R
import com.gaitan.dev.core_ui.utils.LocalSpacing
import com.gaitan.dev.core_ui.utils.TextBasic
import com.gaitan.dev.post_presentation.components.PostItem
import com.gaitan.dev.post_presentation.components.UserItem
import com.gaitan.dev.post_presentation.viewmodel.PostViewModel

@ExperimentalComposeUiApi
@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceMedium)
    ) {

        Spacer(
            modifier = Modifier
                .height(spacing.spaceMedium)
        )
        UserItem(userDetail = state.postsByUser.user)

        Spacer(
            modifier = Modifier
                .height(spacing.spaceMedium)
        )

        TextBasic(
            text = stringResource(R.string.text_title_post),
            style = TextStyle(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(
            modifier = Modifier
                .height(spacing.spaceMedium)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceMedium)
        ) {
            items(state.postsByUser.posts.size) { i ->
                val post = state.postsByUser.posts[i]
                PostItem(postDetail = post)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
        }

    }


}