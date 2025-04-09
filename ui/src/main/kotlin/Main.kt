import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import server.PortableServer
import server.deployVerticle
import ui.ConsolePanel
import ui.RouterListPanel
import ui.TrafficListPanel

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FlowRow(
                maxItemsInEachRow = 2,
            ) {
                RouterListPanel()
                TrafficListPanel()
            }
            ConsolePanel()

        }
    }
}

fun main() = application {
    LaunchedEffect(Unit) {
        deployVerticle() // 서버 실행 먼저
    }

    Tray(
        icon = painterResource("idk.jpeg"),
        menu = {
            Item("Exit", onClick = { exitApplication() })
        }
    )
    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource("idk.jpeg")

    ) {
        val trayState = rememberTrayState()
        val notification = rememberNotification("라우터 추가", "으악", Notification.Type.Warning)

        MenuBar {
            Menu("라우터") {
                Item("라우터 추가", onClick = { trayState.sendNotification(notification) })
                Item("Exit", onClick = { exitApplication() })
            }
            Menu("설정") {
                Item("dummy print", onClick = { println("Setting")  })
            }
        }
        App()
    }
}

object MyAppIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color.Green, Offset(size.width / 4, 0f), Size(size.width / 2f, size.height))
        drawOval(Color.Blue, Offset(0f, size.height / 4), Size(size.width, size.height / 2f))
        drawOval(Color.Red, Offset(size.width / 4, size.height / 4), Size(size.width / 2f, size.height / 2f))
    }
}



object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}