package io.github.pcl_community.pclkmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.github.pcl_community.pclkmp.ui.*
import io.github.pcl_community.pclkmp.ui.frame.Center
import io.github.pcl_community.pclkmp.ui.frame.Sidebar
import io.github.pcl_community.pclkmp.ui.frame.TitleBar
import io.github.pcl_community.pclkmp.ui.pages.DownloadPage
import io.github.pcl_community.pclkmp.ui.pages.LaunchPage
import io.github.pcl_community.pclkmp.ui.pages.OtherPage
import io.github.pcl_community.pclkmp.ui.pages.SettingPage
import pclkmp.composeapp.generated.resources.PCL2_English
import pclkmp.composeapp.generated.resources.Res

@Composable
fun AppFrame() {
    var currentPage by remember { mutableStateOf("launch") }
    val gradient = Brush.linearGradient(
        0.0f to Color(0xFFB1CFD5),
        0.4f to Color(0xFFCBE1F8),
    )

    val cornerRadius = 10.dp

    MaterialTheme(typography = PCLFont()) {
        Column(
            modifier = Modifier
//            .clip(RoundedCornerShape(cornerRadius))
                .background(gradient)
                .fillMaxSize()
        ) {
            TitleBar(onChange = { page -> currentPage = page })
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Sidebar.Render()

                Center.Render()

                when (currentPage) {
                    "launch" -> LaunchPage()
                    "download" -> DownloadPage()
                    "setting" -> SettingPage()
                    "more" -> OtherPage()
                }
            }
        }
    }
}

@Composable
fun PCLFont(): Typography {
    val defaultFontFamily = FontFamily(
        org.jetbrains.compose.resources.Font(
            Res.font.PCL2_English
        )
    )

    return Typography(
        bodySmall = MaterialTheme.typography.bodySmall.copy(
            fontFamily = defaultFontFamily,
        ),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = defaultFontFamily,
        ),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(
            fontFamily = defaultFontFamily,
        ),
        titleSmall = MaterialTheme.typography.titleSmall.copy(
            fontFamily = defaultFontFamily,
        ),
        titleMedium = MaterialTheme.typography.titleMedium.copy(
            fontFamily = defaultFontFamily,
        ),
        titleLarge = MaterialTheme.typography.titleLarge.copy(
            fontFamily = defaultFontFamily,
        ),
        headlineSmall = MaterialTheme.typography.headlineSmall.copy(
            fontFamily = defaultFontFamily,
        ),
        headlineMedium = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = defaultFontFamily,
        ),
        headlineLarge = MaterialTheme.typography.headlineLarge.copy(
            fontFamily = defaultFontFamily,
        ),
    )
}
