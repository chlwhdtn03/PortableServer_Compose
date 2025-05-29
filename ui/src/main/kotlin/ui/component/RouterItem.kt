package ui.component

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.pointerInput
import data.PortableRouter

@Composable
fun RouterItem(router: PortableRouter, onChangeDetail: (router: PortableRouter) -> Unit) {
    ContextMenuArea(items = {
        listOf(
            ContextMenuItem("자세히") {
                onChangeDetail(router)
            },
            ContextMenuItem("수정") {

            },
            ContextMenuItem("삭제") {

            }
        )
    }) {
        Row(
            modifier = Modifier
                .clickable(true, onClick = {})
                .fillMaxWidth()
        ) {
            Text(text = "${router.name} (${router.route}) - ${router.method}")
        }
    }
}

@Composable
fun RouterMenu() {

}