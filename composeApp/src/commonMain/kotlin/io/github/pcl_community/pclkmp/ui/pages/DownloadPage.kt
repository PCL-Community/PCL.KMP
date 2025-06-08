package io.github.pcl_community.pclkmp.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.pcl_community.pclkmp.ui.controls.LeftRadioGroup
import io.github.pcl_community.pclkmp.ui.controls.LeftRadioOption
import io.github.pcl_community.pclkmp.ui.frame.Center
import io.github.pcl_community.pclkmp.ui.frame.Sidebar
import pclkmp.composeapp.generated.resources.*

@Composable
fun DownloadPage(modifier: Modifier = Modifier) {
    var currentPage by remember { mutableStateOf("minecraft") }

    val options = listOf(
        LeftRadioOption("minecraft", "游戏下载", Res.drawable.minecraft),
        LeftRadioOption("mod", "Mod", Res.drawable.mod, size = 22),
        LeftRadioOption("modpack", "整合包", Res.drawable.modpack, size = 20, iconLeftSpace = 4),
        LeftRadioOption("datapack", "数据包", Res.drawable.datapack, size = 24, iconLeftSpace = 2),
        LeftRadioOption("resourcepack", "资源包", Res.drawable.rp, size = 22, iconLeftSpace = 4),
        LeftRadioOption("shaders", "光影包", Res.drawable.shaders, size = 22, iconLeftSpace = 4)
    )

    val separators = listOf(
        Triple(0, "Minecraft", 10),
        Triple(1, "社区资源", 20),
    )

    var selectedOption by remember { mutableStateOf(options[0].value) }

    Sidebar.setContext(width = 150.dp) {
        LeftRadioGroup(
            options = options, selectedValue = selectedOption, onSelected = { newValue ->
                run {
                    selectedOption = newValue
                    currentPage = newValue
                }
            }, textSpaertors = separators
        )
    }

    Center.setContext {
        Text(currentPage)
    }
}