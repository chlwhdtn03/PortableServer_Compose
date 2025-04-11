package ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.PortableTraffic
import java.text.SimpleDateFormat

@Composable
fun TrafficItem(traffic: PortableTraffic) {
    Row(
        modifier = Modifier
            .selectable(true, onClick = {})
            .fillMaxWidth()
    ) {
        Text(text = "${traffic.address} - ${traffic.route}")
    }
}
