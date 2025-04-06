package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
        Text(text = "localhost - /")
        Text("localhost - /")
        Text("localhost - /")
        Text("localhost - /")
    }
}
