package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.ui.theme.Border
import com.vgb3.kstore.ui.theme.Primary
import com.vgb3.kstore.ui.theme.TextPlaceholder
import com.vgb3.kstore.ui.theme.White

@Composable
fun AppBottomBar(
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
    onItemClick: ((itemIdx: Int, destination: Destinations) -> Unit)? = null,
) {
    val shape = RoundedCornerShape(99.dp)
    val shadowColor = Color(0x1A000000)
    val curr = backStack.last()

    BottomAppBar(
        modifier = modifier.height(100.dp),
        contentPadding = PaddingValues(0.dp),
        containerColor = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .height(72.dp)
                .padding(
                    start = 24.dp,
                    end = 24.dp
                )
                .fillMaxWidth()
                .dropShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 15.dp,
                        spread = (-3).dp,
                        color = shadowColor,
                        offset = DpOffset(x = 0.dp, 10.dp)
                    )
                )
                .dropShadow(
                    shape = shape,
                    shadow = Shadow(
                        radius = 6.dp,
                        spread = (-4).dp,
                        color = shadowColor,
                        offset = DpOffset(x = 0.dp, 4.dp)
                    )
                )
                .border(1.dp, Border, shape)
                .clip(shape)
                .background(color = White)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Destinations.entries.forEachIndexed { index, destinations ->
                NavigationBarItem(
                    selected = destinations.route == curr,
                    onClick = { onItemClick?.invoke(index, destinations) },
                    colors = NavigationBarItemDefaults.colors().copy(
                        selectedTextColor = Primary,
                        selectedIconColor = Primary,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedTextColor = TextPlaceholder,
                        unselectedIconColor = TextPlaceholder
                    ),
                    icon = {
                        Box(
                            modifier = Modifier.height(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(destinations.icon),
                                contentDescription = stringResource(destinations.contentDescription)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(destinations.label),
                            lineHeight = 16.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppBottomBarPreview() {
    val backStack = rememberNavBackStack(Destinations.HOME.route)
    AppBottomBar(backStack = backStack)
}