package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import server.portableRouters
import ui.component.RouterItem

/*
 노드 관리 패널
 */
@Composable
fun RouterListPanel() {
    val selectedIndex by remember { mutableStateOf(-1) }
    Column(
        modifier = Modifier.fillMaxWidth(0.7f)
            .fillMaxHeight(0.6f)
            .background(Color.LightGray)
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(portableRouters) { index ->
                RouterItem(index)
            }
        }
    }
}
