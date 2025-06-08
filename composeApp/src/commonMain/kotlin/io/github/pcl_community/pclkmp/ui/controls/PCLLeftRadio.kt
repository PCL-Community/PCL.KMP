package io.github.pcl_community.pclkmp.ui.controls

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PCLLeftRadio(
    size: Int,
    text: String,
    icon: DrawableResource,
    selected: Boolean = false,
    onSelected: () -> Unit,
    selectedColor: Color,
    hoverColor: Color,
    hoverBorderColor: Color,
    animationDuration: Int,
    iconLeftSpace: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val targetBackgroundColor by derivedStateOf {
        when {
            isHovered -> hoverColor
            else -> Color.Transparent
        }
    }

    val targetContentColor by derivedStateOf {
        if (selected) selectedColor else Color(0xFF343E49)
    }

    val animatedBackgroundColor by animateColorAsState(
        targetValue = targetBackgroundColor,
        animationSpec = tween(durationMillis = animationDuration),
        label = "backgroundColorAnimation"
    )

    val animatedContentColor by animateColorAsState(
        targetValue = targetContentColor,
        animationSpec = tween(durationMillis = animationDuration),
        label = "contentColorAnimation"
    )

    val targetPColor by derivedStateOf {
        if (selected) selectedColor else Color.Transparent
    }

    val animatedPColor by animateColorAsState(
        targetValue = targetPColor,
        animationSpec = tween(durationMillis = animationDuration),
        label = "contentColorAnimation"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onSelected
            )
            .height(35.dp)
            .border(
                width = 2.dp,
                color = animatedBackgroundColor
            )
            .background(
                color = animatedBackgroundColor,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(23.dp)
                .offset(x = (-5).dp)
                .background(
                    color = animatedPColor,
                    shape = RoundedCornerShape(2.dp)
                )
        )

        Spacer(modifier = Modifier.width(iconLeftSpace.dp))
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = animatedContentColor,
            modifier = Modifier.size(size.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = animatedContentColor,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(12.dp))
    }
}

@Composable
fun <T> LeftRadioGroup(
    options: List<LeftRadioOption<T>>,
    selectedValue: T,
    onSelected: (T) -> Unit,
    textSpaertors: List<Triple<Int, String, Int>> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        var currentIndex = 0

        options.forEachIndexed { index, option ->
            if (textSpaertors.any { it.first == currentIndex }) {
                val separatorText = textSpaertors.find { it.first == currentIndex }?.second ?: ""
                val height = textSpaertors.find { it.first == currentIndex }?.third ?: 0
                Spacer(modifier = Modifier.height(height.dp))
                Text(
                    text = "  $separatorText",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 0.dp)
                )
            }

            PCLLeftRadio(
                text = option.text,
                icon = option.icon,
                selected = selectedValue == option.value,
                onSelected = { onSelected(option.value) },
                selectedColor = option.selectedColor,
                hoverColor = option.hoverColor,
                hoverBorderColor = option.hoverBorderColor,
                animationDuration = option.animationDuration,
                size = option.size,
                iconLeftSpace = option.iconLeftSpace
            )

            currentIndex++
        }
    }
}

data class LeftRadioOption<T>(
    val value: T,
    val text: String,
    val icon: DrawableResource,
    val selectedColor: Color = Color(0xFF127AE0),
    val hoverColor: Color = Color(0xFF127AE0).copy(alpha = 0.2f),
    val hoverBorderColor: Color = Color(0xCDE3FB).copy(alpha = 0.2f),
    val animationDuration: Int = 200,
    val size: Int = 20,
    val iconLeftSpace: Int = 2
)