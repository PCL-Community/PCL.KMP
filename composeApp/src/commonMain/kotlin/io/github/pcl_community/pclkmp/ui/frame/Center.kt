package io.github.pcl_community.pclkmp.ui.frame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Center {
    private var _content by mutableStateOf<@Composable () -> Unit?>({})

    @Composable
    fun setContext(content: @Composable () -> Unit) {
        LaunchedEffect(Unit) {
            _content = content
        }
    }

    @Composable
    fun Render() {
        val currentContent = _content

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            currentContent()
        }
    }
}