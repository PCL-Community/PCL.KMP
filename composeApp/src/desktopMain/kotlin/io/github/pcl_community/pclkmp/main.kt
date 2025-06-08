package io.github.pcl_community.pclkmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import io.github.pcl_community.pclkmp.States.windowState

object States{
    val windowState = WindowState(
        placement = WindowPlacement.Floating,
        isMinimized = false,
    )
}
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Plain Craft Launcher KMP",
        state = windowState,
//        undecorated = true,
//        transparent = true
    ) {
        AppFrame()
    }

}