package Panel

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import java.io.OutputStream
import java.io.PrintStream

/*
 노드 관리 패널
 */
@Composable
fun PortablePanel() {
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

/*
    접속 기록 확인 패널
 */
@Composable
fun TrafficPanel() {
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

/*
    프로세스 출력 패널
 */
@Composable
fun ConsolePanel() {
    var input by remember { mutableStateOf("") }
    val console = remember { mutableStateOf("") }

    ConsoleStream(console)

    Column(
        modifier = Modifier.fillMaxHeight().background(Color.Yellow)

    ) {
        Box (
            modifier = Modifier.fillMaxHeight(0.8f)
        ) {

            val stateVertical = rememberScrollState(0)
            Box(modifier = Modifier.fillMaxSize().verticalScroll(stateVertical)) {
                Text(
                    modifier = Modifier.fillMaxSize().background(Color.Magenta)
                        .fillMaxHeight(0.8f),
                    text = console.value,
                )
            }

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(stateVertical)
            )

            LaunchedEffect(console.value) { // console에 내용이 바뀔 때마다 실행됨
                if(stateVertical.maxValue <= stateVertical.value + stateVertical.viewportSize) {
                    stateVertical.animateScrollTo(stateVertical.maxValue)
                }
            }
        }

        BasicTextField(
            modifier = Modifier.background(Color.LightGray).weight(1f),
            value = input,
            maxLines = 1,
            singleLine = true,
            onValueChange = { input = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if(input.isNotBlank()) {
                        println(input)
                        input = ""
                    }
                }
            ),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.wrapContentHeight(Alignment.CenterVertically).fillMaxWidth().padding(Dp(5f))) {
                    innerTextField()
                }
            }
        )





    }



}

/*
    OutputStream과 PrintStream을 이용하여 println되는 내용들을 콘솔창이 아닌 다른 OutputStream으로 돌릴 수 있음.
    새로운 OutputStream 클래스를 생성하여 write 될 때, 콘솔창에 띄우는 것이 아닌, 컴포저블 String 변수에 추가해줌.
    추가해주면 리컴포징 되면서 콘솔창에 뜰 것
 */
@Composable
fun ConsoleStream(output: MutableState<String>) {

    val outputStream = remember {
        object : OutputStream() {
            override fun write(b: Int) { // 단일 바이트 처리
                output.value += b.toChar()
            }

            override fun write(b: ByteArray) { // 2 바이트 이상 처리
                write(b, 0, b.size)
            }

            override fun write(b: ByteArray, off: Int, len: Int) { // 2 바이트 이상 처리
                output.value += String(b, off, len)
            }
        }
    }

    val printStream = remember { PrintStream(outputStream) } // 위 outputStream과 연결

    LaunchedEffect(Unit) { // 단 1번만 실행
        System.setOut(printStream) // 출력스트림 변경
        println("PrintStream 초기화 완료")
    }

}