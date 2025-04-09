package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import server.portableTraffics
import ui.component.TrafficItem

/*
    접속 기록 확인 패널
 */
@Composable
fun TrafficListPanel() {
    Column(
        modifier = Modifier.fillMaxWidth(0.3f)
            .fillMaxHeight(0.6f)
            .background(Color.Cyan)
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(portableTraffics) { index ->
                TrafficItem(index)
            }
        }
    }
}
