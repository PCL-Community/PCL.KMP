package io.github.pcl_community.pclkmp.ui.controls

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
fun PCLRadioButton(
    size: Int,
    text: String,
    icon: DrawableResource,
    selected: Boolean = false,
    onSelected: () -> Unit,
    selectedColor: Color,
    selectedBackgroundColor: Color,
    unselectedColor: Color,
    unselectedBackgroundColor: Color = Color.White.copy(alpha = 0.1f),
    hoverColor: Color = Color.White.copy(alpha = 0.2f),
    pressedColor: Color = Color.White.copy(alpha = 0.4f),
    animationDuration: Int = 200
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val targetBackgroundColor by derivedStateOf {
        when {
            selected -> selectedBackgroundColor
            isPressed -> pressedColor
            isHovered -> hoverColor
            else -> unselectedBackgroundColor
        }
    }

    val targetContentColor by derivedStateOf {
        if (selected) selectedColor else unselectedColor
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

    Row(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onSelected
            )
            .padding(vertical = 8.dp)
            .height(25.dp)
            .background(
                color = animatedBackgroundColor,
                shape = RoundedCornerShape(20.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))
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
fun <T> RadioGroup(
    options: List<RadioButtonOption<T>>,
    selectedValue: T,
    onSelected: (T) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        options.forEach { option ->
            PCLRadioButton(
                text = option.text,
                icon = option.icon,
                selected = selectedValue == option.value,
                onSelected = { onSelected(option.value) },
                selectedColor = option.selectedColor,
                unselectedColor = option.unselectedColor,
                selectedBackgroundColor = option.selectedBackgroundColor,
                unselectedBackgroundColor = option.unselectedBackgroundColor,
                hoverColor = option.hoverColor,
                pressedColor = option.pressedColor,
                animationDuration = option.animationDuration,
                size = option.size
            )
        }
    }
}

data class RadioButtonOption<T>(
    val value: T,
    val text: String,
    val icon: DrawableResource,
    val selectedColor: Color = Color(0xFF127AE0),
    val selectedBackgroundColor: Color = Color(0xFEFEFEFE),
    val unselectedColor: Color = Color.White,
    val unselectedBackgroundColor: Color = Color.White.copy(alpha = 0f),
    val hoverColor: Color = Color.White.copy(alpha = 0.2f),
    val pressedColor: Color = Color.White.copy(alpha = 0.4f),
    val animationDuration: Int = 200,
    val size: Int = 15
)