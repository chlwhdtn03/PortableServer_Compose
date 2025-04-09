package ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.PortableRouter

@Composable
fun RouterItem(router: PortableRouter) {
    Row(
        modifier = Modifier
            .selectable(true, onClick = {})
            .fillMaxWidth()
    ) {
        Text(text = "${router.name} (${router.route}) - ${router.method}")
    }
}