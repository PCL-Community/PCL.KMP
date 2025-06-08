package io.github.pcl_community.pclkmp

interface Platform {
    fun close()
    fun minimize()
}

expect fun getPlatform(): Platform