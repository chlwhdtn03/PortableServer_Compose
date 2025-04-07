package ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/*
 노드 관리 패널
 */
@Composable
fun RouterListPanel() {
    Column(
        modifier = Modifier.fillMaxWidth(0.7f)
            .fillMaxHeight(0.6f)
            .background(Color.LightGray)
    ) {
        Row {
            Text(text = "root (/) - GET")
        }
        Row {
            Text(text = "addUser (/addUser) - POST")
        }
    }
}
