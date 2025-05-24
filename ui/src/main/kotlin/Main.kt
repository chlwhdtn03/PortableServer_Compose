import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import server.deployVerticle
import ui.ConsolePanel
import ui.RouterListPanel
import ui.TrafficListPanel

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val console = remember { mutableStateOf("") }

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        val contextMenuRepresentation = if (isSystemInDarkTheme()) {
            DarkDefaultContextMenuRepresentation
        } else {
            LightDefaultContextMenuRepresentation
        }
        CompositionLocalProvider(LocalContextMenuRepresentation provides contextMenuRepresentation) {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FlowRow(
                    maxItemsInEachRow = 2,
                ) {
                    RouterListPanel()
                    TrafficListPanel(console)
                }
                ConsolePanel(console)

            }
        }
    }
}

@Composable
fun Splash() {
    MaterialTheme {
        Text("Splash!")
    }
}

fun main() = application {
    var askingClose by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        deployVerticle() // 서버 실행 먼저
    }

    Tray(
        icon = painterResource("idk.jpeg"),
        menu = {
            Item("Exit", onClick = { askingClose = true })
        }
    )
    Window(
        onCloseRequest = {  askingClose = true },
        icon = painterResource("idk.jpeg")

    ) {
        val trayState = rememberTrayState()
        val notification = rememberNotification("라우터 추가", "으악", Notification.Type.Warning)

        if(askingClose) { // TODO tray 구현 https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-desktop-top-level-windows-management.html#minimize-a-window-to-the-system-tray
            DialogWindow(
                onCloseRequest = { askingClose = false },
                undecorated = true,
                resizable = false,
                title = "종료"
            ) {
                Column {
                    DialogTitleBar(title = "종료")
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text("정말로 종료하시겠습니까?")
                        Text("서버도 함께 종료됩니다.")
                        Row {
                            Button(onClick = {
                                exitApplication()
                            }) {
                                Text("종료")
                            }
                            Button(onClick = {
                                askingClose = false
                            }) {
                                Text("취소")
                            }
                        }
                    }
                }
            }
        }

        MenuBar {
            Menu("라우터") {
                Item("라우터 추가", onClick = { trayState.sendNotification(notification) })
                Item("Exit", onClick = { askingClose = false })
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

@Composable
private fun WindowScope.DialogTitleBar(
    title: String = "Dialog Window",
) = WindowDraggableArea {
    Box(Modifier.fillMaxWidth().height(48.dp).background(MaterialTheme.colorScheme.secondary)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 12.dp),
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawOval(Color(0xFFFFA500))
    }
}