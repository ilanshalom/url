package com.example.mfpledon.demoviewumaurl

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myWebView1: WebView = findViewById(R.id.webView1)
        if (verificaConexaoInternet()) { //veja abaixo esta forma de verificar conexão
            myWebView1.settings.javaScriptEnabled = true
            //supportActionBar?.hide()  //para ocultar a Action Bar
            myWebView1.webViewClient = WebViewClient()
            //caso o código JavaScript acesse a localStorage, também adicionar:
            //myWebView1.settings.setDomStorageEnabled(true)
            myWebView1.loadUrl("http://mfpledon.com.br/quiz")  //http://www.dxomark.com/  http://www.dpreview.com
        } else {
            myWebView1.visibility = View.INVISIBLE
            val viewMsg: TextView = findViewById(R.id.textViewMsg)
            viewMsg.visibility = View.VISIBLE
            viewMsg.text = "Sem Internet. Este app necessita conexão com Internet. Verifique, por favor."
        }
    }

    fun verificaConexaoInternet(): Boolean {
        val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null)
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                    return true
        }
        return false
    }
}

