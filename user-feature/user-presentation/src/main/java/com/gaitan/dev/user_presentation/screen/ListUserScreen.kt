package com.gaitan.dev.user_presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gaitan.dev.core_ui.utils.LocalSpacing
import com.gaitan.dev.core_ui.utils.TextBasic
import com.gaitan.dev.core.R
import com.gaitan.dev.core.navigation.Screen
import com.gaitan.dev.user_presentation.components.SearchFieldView
import com.gaitan.dev.user_presentation.components.UserDetailView
import com.gaitan.dev.user_presentation.trasactions.UserEvent
import com.gaitan.dev.user_presentation.viewmodel.UserViewModel

@ExperimentalComposeUiApi
@Composable
fun ListUserScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: UserViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    Column {
        SearchFieldView(
            text = state.searchText,
            onValueChange = {
                viewModel.onEvent(UserEvent.OnSearchTextChange(it))
            }
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 5.dp)
            ) {
                if (!state.isGetUserService) {
                    viewModel.onEvent(UserEvent.OnGetUsers)
                } else {
                    items(state.getUsers.size) { i ->
                        val user = state.getUsers[i]
                        UserDetailView(
                            userView = user,
                            onActionClick = {navController.navigate(Screen.AllPostByUserScreen.route + "/${user.id}" )}
                        )

                    }
                }

            }

        }


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isSearching || state.isLoading -> CircularProgressIndicator()
                state.getUsers.isEmpty() -> {
                    Text(
                        text = stringResource(id = R.string.text_error_empty),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }


    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.getUsers.isEmpty() -> {
                TextBasic(text = stringResource(id = R.string.text_error_empty))
            }
        }

    }



}