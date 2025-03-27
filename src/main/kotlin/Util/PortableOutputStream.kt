package Util

import java.io.OutputStream

class PortableOutputStream(text: String) : OutputStream() {
    private var _text = text
    override fun write(b: Int) {
        _text += b.toChar()
    }

}