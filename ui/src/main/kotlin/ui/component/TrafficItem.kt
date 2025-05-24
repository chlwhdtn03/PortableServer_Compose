package ui.component

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.PortableTraffic
import java.text.SimpleDateFormat

@Composable
fun TrafficItem(traffic: PortableTraffic) {
    ContextMenuArea(items = {
        listOf(
            ContextMenuItem("자세히") {

            },
            ContextMenuItem("IP 접속 차단") {

            },
            ContextMenuItem("기록 삭제") {

            }
        )
    }) {
        Row(
            modifier = Modifier
                .selectable(true, onClick = {})
                .fillMaxWidth()
        ) {
            Text(text = "${traffic.address} - ${traffic.route}")
        }
    }
}
