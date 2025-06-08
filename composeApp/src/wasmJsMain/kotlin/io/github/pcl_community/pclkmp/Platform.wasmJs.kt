package io.github.pcl_community.pclkmp

class WasmPlatform: Platform {
    override fun close() {
    }

    override fun minimize() {
    }
}

actual fun getPlatform(): Platform = WasmPlatform()