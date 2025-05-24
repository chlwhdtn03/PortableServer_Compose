package ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.io.OutputStream
import java.io.PrintStream

/*
    프로세스 출력 패널
 */
@Composable
fun ConsolePanel(console: MutableState<String>) {
    var input by remember { mutableStateOf("") }

    ConsoleStream(console)

    Column(
        modifier = Modifier.fillMaxHeight().border(1.dp, Color.DarkGray),
    ) {
        Box (
            modifier = Modifier.fillMaxHeight(0.8f).background(Color.Black)
        ) {

            val stateVertical = rememberScrollState(0)
            Box(modifier = Modifier.fillMaxSize().verticalScroll(stateVertical)) {
                Text(
                    modifier = Modifier.fillMaxSize()
                        .fillMaxHeight(0.8f),
                    text = console.value,
                    style = TextStyle(Color.White, fontWeight = FontWeight.SemiBold)
                )
            }

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().background(Color.LightGray).padding(2.dp),
                adapter = rememberScrollbarAdapter(stateVertical),

                )

            LaunchedEffect(console.value) { // console에 내용이 바뀔 때마다 실행됨
                if(stateVertical.maxValue <= stateVertical.value + stateVertical.viewportSize) {
                    stateVertical.animateScrollTo(stateVertical.maxValue)
                }
            }
        }

        BasicTextField(
            modifier = Modifier.weight(1f).background(Color.Black),
            value = input,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(Color.White, fontWeight = FontWeight.SemiBold),
            onValueChange = { input = it },
            cursorBrush = SolidColor(Color.White),
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
                    Text("> ", style = TextStyle(Color.White, fontWeight = FontWeight.SemiBold))
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
private fun ConsoleStream(output: MutableState<String>) {

    val outputStream = remember {
        object : OutputStream() {
            init {
                println("OutputStream created")
            }
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
        println("printStream이 연결되었습니다.")
    }

}