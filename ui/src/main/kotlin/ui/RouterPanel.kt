package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.PortableRouter
import server.portableRouters
import ui.component.RouterItem

/*
 노드 관리 패널
 */
@Composable
fun RouterListPanel(onChangeDetail: (router: PortableRouter) -> Unit) {
    val selectedIndex by remember { mutableStateOf(-1) }
    Column(
        modifier = Modifier
//            .background(Color.LightGray)
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(2.dp)
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(portableRouters) { index ->
                RouterItem(index, onChangeDetail)
            }
        }
    }
}
