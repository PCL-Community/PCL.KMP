package io.github.pcl_community.pclkmp.ui.controls

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PCLCard(
    title: String,
    content: @Composable () -> Unit,
    animationDuration: Int = 200,
    modifier: Modifier = Modifier,
    collapsible: Boolean = false,
    initiallyExpanded: Boolean = true
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val titleColor by animateColorAsState(
        targetValue = if (isHovered) Color(0xFF0F64B7) else Color(0xFF343F49),
        animationSpec = tween(durationMillis = animationDuration)
    )

    val shadowColor by animateColorAsState(
        targetValue = if (isHovered) Color(0xFF0F64B7) else Color.DarkGray,
        animationSpec = tween(durationMillis = animationDuration)
    )

    val buttonRotation by animateFloatAsState(
        targetValue = if (expanded) 0f else 180f, animationSpec = tween(durationMillis = animationDuration)
    )

    Column(
        modifier = modifier
            .shadow(
                spotColor = shadowColor,
                elevation = 18.dp,
                shape = RoundedCornerShape(10)
            )
            .clip(RoundedCornerShape(10))
            .background(Color(0xFAFBFEFF))
            .padding(10.dp)
            .fillMaxWidth()
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {})
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    interactionSource = null,
                    onClick = { if (collapsible) expanded = !expanded },
                    indication = null,
                )
        ) {
            Text(
                text = title, fontWeight = FontWeight.Bold, color = titleColor, modifier = Modifier.weight(1f)
            )

            if (collapsible) {
                Icon(
                    imageVector = Icons.Default.ExpandLess,
                    contentDescription = "",
                    tint = Color(0xFF6B7280),
                    modifier = Modifier.size(24.dp).rotate(buttonRotation),
                )
            }
        }
        Box(
            modifier = Modifier.animateContentSize(
                animationSpec = tween(durationMillis = 120)
            ).heightIn(min = if (expanded) 0.dp else Dp.Unspecified)
        ) {
            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                content()
            }
        }
    }
}