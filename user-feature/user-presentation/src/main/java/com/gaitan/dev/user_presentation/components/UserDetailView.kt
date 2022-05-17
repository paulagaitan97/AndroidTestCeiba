package com.gaitan.dev.user_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.gaitan.dev.core.R
import com.gaitan.dev.core_ui.utils.LocalExtrasView
import com.gaitan.dev.core_ui.utils.LocalSpacing
import com.gaitan.dev.core_ui.utils.TextBasic
import com.gaitan.dev.user_domain.model.User


@Composable
fun UserDetailView(
    userView: User,
    modifier: Modifier = Modifier,
    onActionClick: (String) -> Unit
) {
    val spacing = LocalSpacing.current
    val extrasView = LocalExtrasView.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceMedium),
        elevation = extrasView.elevationMedium
    ) {
        Column(
            modifier = Modifier.padding(extrasView.elevationLarge)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                TextBasic(
                    text = userView.name,
                    style = TextStyle(
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colors.primary,
                        fontSize = 20.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            Row {
                Icon(
                    imageVector = Icons.Rounded.Phone,
                    contentDescription = stringResource(id = R.string.text_description_icon_phone),
                    tint = Color(0xFF428646)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                TextBasic(text = userView.phone)
            }

            Row {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription =  stringResource(id = R.string.text_description_icon_email),
                    tint = Color(0xFF428646)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                TextBasic(text = userView.email)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),

                ) {
                TextButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .testTag("TagButtonPost"),
                    onClick = { onActionClick(userView.id.toString()) },
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent)

                ) {
                    Text(
                        text = stringResource(R.string.text_button_post),
                        color = Color(0xFF428646),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }

}