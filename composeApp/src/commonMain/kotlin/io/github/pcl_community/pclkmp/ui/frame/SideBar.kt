package io.github.pcl_community.pclkmp.ui.frame

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Sidebar {
    private var targetWidth by mutableStateOf(285.dp)
    private var _content by mutableStateOf<@Composable () -> Unit?>({})

    val width: Dp
        @Composable get() = targetWidth

    @Composable
    fun setContext(width: Dp, content: @Composable () -> Unit) {
        LaunchedEffect(Unit) {
            targetWidth = width
            _content = content
        }
    }

    @Composable
    fun Render() {
        val animatedWidth by animateDpAsState(
            targetValue = targetWidth,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = 1400f,
            )
        )
        val currentContent = _content

        if (animatedWidth > 0.dp) {
            Column(
                modifier = Modifier
                    .width(animatedWidth)
                    .fillMaxHeight()
                    .shadow(elevation = 4.dp)
                    .background(Color(0xFCFCFEFC))
            ) {
                currentContent()
            }
        }
    }
}