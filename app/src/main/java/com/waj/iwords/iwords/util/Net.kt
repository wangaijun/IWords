package util

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object Net{
    fun postJsonRequest(urlStr: String, body:String):String{
        return jsonRequest(urlStr,body,"POST")
    }

    fun putJsonRequest(urlStr: String, body:String):String{
        return jsonRequest(urlStr,body,"PUT")
    }

    private fun jsonRequest(urlStr: String, body:String, type:String):String{
        val url = URL(urlStr)
        val conn = url.openConnection()
        conn as HttpURLConnection
        conn.doOutput = true
        conn.requestMethod = type
        conn.addRequestProperty("Accept-Charset", "utf-8")
        conn.addRequestProperty("Content-Type", "application/json")
        conn.addRequestProperty("X-LC-Id", "P0X9CEHNcfck0qvkuEaP3Dc7-gzGzoHsz")
        conn.addRequestProperty("X-LC-Key", "WL5GuL34xuyg1UFRRbr3lnuv")
        val os = conn.outputStream
        os.write(body.toByteArray())
        os.flush()
        val ins = conn.inputStream
        val bis = BufferedInputStream(ins)
        val bs = ByteArray(1024*2)
        val len = bis.read(bs)
        return String(bs,0,len)
    }

    fun getRequest(urlStr: String,requestParams: Map<String, String>):String{
        val sb = StringBuilder(urlStr)
        sb.append("?")
        val it = requestParams.iterator()
        while (it.hasNext()){
            val e = it.next()
            val k = e.key
            val v = e.value
            sb.append(URLEncoder.encode(k))
            sb.append("=")
            sb.append(URLEncoder.encode(v))
            sb.append("&")
        }
        val url = URL(sb.toString())
        val conn = url.openConnection()
        conn as HttpURLConnection
        conn.addRequestProperty("Accept-Charset", "utf-8")
        conn.addRequestProperty("X-LC-Id", "P0X9CEHNcfck0qvkuEaP3Dc7-gzGzoHsz")
        conn.addRequestProperty("X-LC-Key", "WL5GuL34xuyg1UFRRbr3lnuv")
        val ins = conn.inputStream
        val bis = BufferedInputStream(ins)
        val bs = ByteArray(2*1024*1024)
        val len = bis.read(bs)
        return String(bs,0,len)
    }
}

