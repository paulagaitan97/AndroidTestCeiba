package com.gaitan.dev.post_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.gaitan.dev.core.R
import com.gaitan.dev.core_ui.utils.LocalExtrasView
import com.gaitan.dev.core_ui.utils.LocalSpacing
import com.gaitan.dev.core_ui.utils.TextBasic
import com.gaitan.dev.post_domain.model.User

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    userDetail: User
) {
    val spacing = LocalSpacing.current
    val extrasView = LocalExtrasView.current


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceExtraSmall),
        elevation = extrasView.elevationMedium
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextBasic(
                    text = userDetail.name,
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
                TextBasic(text = userDetail.phone)
            }

            Row {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = stringResource(id = R.string.text_description_icon_email),
                    tint = Color(0xFF428646)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                TextBasic(text = userDetail.email)
            }
        }
    }

}