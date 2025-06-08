package io.github.pcl_community.pclkmp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import java.awt.MouseInfo
import java.awt.Point
import kotlin.system.exitProcess


class JVMPlatform : Platform {
    override fun close() {
        exitProcess(0)
    }

    override fun minimize() {
        States.windowState.isMinimized = true
    }
}

actual fun getPlatform(): Platform = JVMPlatform()