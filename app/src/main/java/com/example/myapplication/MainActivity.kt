package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var messageTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageTV = findViewById(R.id.idMessage)

        val uri: Uri? = intent.data

        val msg: String? = getMessageFromDeepLink(uri)
        if (msg == null)
            messageTV?.setText("No input message")
        else
            messageTV?.setText(msg)
    }

    fun getMessageFromDeepLink(uri: Uri?): String? {
        var msg: String? = null
        if (uri != null) {
            msg = uri.getQueryParameter("msg") ?: return null

            msg?.let {
                if (it.contains("+")) {
                    msg = it.replace("+", " ")
                }
            }
        }
        return msg
    }
}
