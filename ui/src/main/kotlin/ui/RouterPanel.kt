package ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
