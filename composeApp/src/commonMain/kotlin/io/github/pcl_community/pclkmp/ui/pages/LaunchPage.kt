package io.github.pcl_community.pclkmp.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.pcl_community.pclkmp.Greet
import io.github.pcl_community.pclkmp.ui.controls.PCLCard
import io.github.pcl_community.pclkmp.ui.frame.Center
import io.github.pcl_community.pclkmp.ui.frame.Sidebar

@Composable
fun LaunchPage(modifier: Modifier = Modifier) {
    Sidebar.setContext(width = 285.dp) {

    }

    Center.setContext {
        PCLCard("PCL.KMP",{
            Text(Greet.hello())
        })
    }
}
