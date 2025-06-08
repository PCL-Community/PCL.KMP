package io.github.pcl_community.pclkmp.ui.frame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.pcl_community.pclkmp.getPlatform
import io.github.pcl_community.pclkmp.ui.controls.RadioButtonOption
import io.github.pcl_community.pclkmp.ui.controls.RadioGroup
import org.jetbrains.compose.resources.painterResource
import pclkmp.composeapp.generated.resources.*

@Composable
fun TitleBar(modifier: Modifier = Modifier, onChange: (String) -> Unit) {
    val options = listOf(
        RadioButtonOption(
            value = "launch", text = "启动", icon = Res.drawable.launch
        ), RadioButtonOption(
            value = "download", text = "下载", icon = Res.drawable.download
        ), RadioButtonOption(
            value = "setting", text = "设置", icon = Res.drawable.settings, size = 20
        ), RadioButtonOption(
            value = "more", text = "更多", icon = Res.drawable.more
        )
    )

    var selectedOption by remember { mutableStateOf(options[0].value) }

    val gradient = Brush.horizontalGradient(
        0.0f to Color(0xFF106AC4), 0.5f to Color(0xFF1277DD), 1.0f to Color(0xFF106AC4)
    )

    Row(
        modifier = modifier.background(gradient)
    ) {
        Box(
            Modifier.padding(8.dp), contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(Res.drawable.pcl),
                modifier = Modifier.offset(y = 4.dp).width(80.dp).height(20.dp),

                contentDescription = null
            )
        }

        Row(
            modifier = Modifier.offset(y = 4.dp).weight(1f).wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioGroup(
                options = options, selectedValue = selectedOption, onSelected = { newValue ->
                    selectedOption = newValue
                    onChange(newValue)
                })
        }

        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = { getPlatform().minimize() }, modifier = Modifier.padding(end = 40.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.min),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }
            IconButton(
                onClick = { getPlatform().close() },
            ) {
                Image(
                    painter = painterResource(Res.drawable.close),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}