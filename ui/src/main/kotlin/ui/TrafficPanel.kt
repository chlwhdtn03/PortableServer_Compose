package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.PortableTraffic
import server.portableTraffics
import ui.component.TrafficItem

/*
    접속 기록 확인 패널
 */
@Composable
fun TrafficListPanel(console: MutableState<String>) {

    // Console에 접속이 감지되었다는 메세지(사실 어떤 메세지에서든 작동하긴 함)
    val rememberTraffics = remember(console.value) { portableTraffics.toList() }

    Column(
        modifier = Modifier.fillMaxWidth(0.3f)
            .fillMaxHeight(0.6f)
            .background(Color.Cyan)
    ) {
        LazyColumn(
            modifier = Modifier,
            reverseLayout = true
        ) {
            items(rememberTraffics) { index ->
                TrafficItem(index)
            }
        }
    }
}
