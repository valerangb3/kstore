package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.Border
import com.vgb3.kstore.ui.theme.CircleColorShape
import com.vgb3.kstore.ui.theme.InsideBorderColor
import com.vgb3.kstore.ui.theme.PrimaryIconTint
import com.vgb3.kstore.ui.theme.Success
import com.vgb3.kstore.ui.theme.White

@Composable
fun EmptyVaultData(
    modifier: Modifier = Modifier,
    mainIcon: @Composable () -> Unit = {},
    borderTopEndCircleContent: (@Composable () -> Unit)? = null,
    borderBottomStartContent: (@Composable () -> Unit)? = null,
    showInnerBorder: Boolean = true,
) {
    val commonSize = 280.dp
    val innerContainerSize = commonSize - 24.dp
    Box(
        modifier = modifier
            .size(commonSize)
            .background(
                CircleColorShape,
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (showInnerBorder) {
            Canvas(
                modifier = Modifier
                    .size(commonSize)
                    .padding(12.dp)
            ) {
                drawCircle(
                    color = InsideBorderColor,
                    radius = size.minDimension / 2,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(15f, 15f),
                            phase = 0f
                        )
                    )
                )
            }
        }
        Box(
            modifier = Modifier.size(innerContainerSize),
            contentAlignment = Alignment.Center
        ) {
            val shape = CircleShape
            val shadowColor = Color(0x1A000000)
            Box(
                modifier = Modifier
                    .size(
                        width = 124.dp,
                        height = 132.dp
                    )
                    .dropShadow(
                        shape = shape,
                        shadow = Shadow(
                            color = shadowColor,
                            spread = (-5).dp,
                            offset = DpOffset(0.dp, 20.dp),
                            radius = 25.dp
                        )
                    )
                    .dropShadow(
                        shape = shape,
                        shadow = Shadow(
                            color = shadowColor,
                            spread = (-6).dp,
                            offset = DpOffset(0.dp, 8.dp),
                            radius = 10.dp
                        )
                    )
                    .background(color = White, shape = shape)
                    .border(
                        width = 1.dp,
                        color = Border,
                        shape = shape
                    ),
                contentAlignment = Alignment.Center
            ) {
                mainIcon()
            }
            borderTopEndCircleContent?.let { showContent ->
                val shape = CircleShape
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.TopEnd)
                        .offset(
                            x = (-16).dp,
                            y = 16.dp
                        )
                        .dropShadow(
                            shape = shape,
                            shadow = Shadow(
                                color = shadowColor,
                                spread = (-1).dp,
                                offset = DpOffset(0.dp, 4.dp),
                                radius = 6.dp
                            ),
                        )
                        .dropShadow(
                            shape = shape,
                            shadow = Shadow(
                                color = shadowColor,
                                spread = (-2).dp,
                                offset = DpOffset(0.dp, 2.dp),
                                radius = 6.dp
                            ),
                        )
                        .background(
                            White,
                            shape
                        )
                        .border(
                            width = 1.dp,
                            color = Border,
                            shape = shape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    showContent()
                }
            }
            borderBottomStartContent?.let { showContent ->
                val shape = RoundedCornerShape(16.dp)
                Box(
                    modifier = Modifier
                        .size(
                            width = 36.dp,
                            height = 28.dp
                        )
                        .align(Alignment.BottomStart)
                        .offset(
                            x = 8.dp,
                            y = (-24).dp
                        )
                        .dropShadow(
                            shape = shape,
                            shadow = Shadow(
                                color = shadowColor,
                                spread = (-1).dp,
                                offset = DpOffset(0.dp, 4.dp),
                                radius = 6.dp
                            ),
                        )
                        .dropShadow(
                            shape = shape,
                            shadow = Shadow(
                                color = shadowColor,
                                spread = (-2).dp,
                                offset = DpOffset(0.dp, 2.dp),
                                radius = 6.dp
                            ),
                        )
                        .clip(shape)
                        .background(White)
                        .border(
                            width = 1.dp,
                            color = Border,
                            shape = shape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    showContent()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmptyVaultDataPreview() {
    EmptyVaultData(
        mainIcon = {
            Icon(
                painter = painterResource(R.drawable.account_secure),
                contentDescription = null,
                tint = PrimaryIconTint
            )
        },
        borderTopEndCircleContent = {
            Icon(
                painter = painterResource(R.drawable.success_check_mark),
                contentDescription = null,
                tint = Success
            )
        },
        borderBottomStartContent = {
            Icon(
                painter = painterResource(R.drawable.key_icon),
                contentDescription = null,
                tint = PrimaryIconTint
            )
        }
    )
}