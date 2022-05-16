package com.gaitan.dev.post_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.gaitan.dev.core_ui.utils.LocalExtrasView
import com.gaitan.dev.core_ui.utils.LocalSpacing
import com.gaitan.dev.core_ui.utils.TextBasic
import com.gaitan.dev.post_domain.model.Post

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    postDetail: Post
) {
    val spacing = LocalSpacing.current
    val extrasView = LocalExtrasView.current

    Card(
        modifier = Modifier
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
                    text = postDetail.title,
                    style = TextStyle(
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colors.primary,
                        fontSize = 18.sp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }

            Spacer(
                modifier = Modifier
                    .height(spacing.spaceMedium)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextBasic(
                    text = postDetail.body,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 8,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}
