package dialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import data.PortableRouter

@Preview
@Composable
fun DetailDialog(router: PortableRouter) {
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = router.name,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "위치 : ${router.route} (${router.method})",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
