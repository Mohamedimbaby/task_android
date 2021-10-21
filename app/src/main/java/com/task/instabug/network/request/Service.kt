package com.task.instabug.network.request

import com.task.instabug.network.mappers.ignoreSpecialCharacters
import com.task.instabug.network.models.WordsResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

class Service(private val requestUrl: String) : RemoteDs {

    override fun fetchHtmlResponse(): WordsResponse {
        val content = StringBuilder()
        val dataResponse = WordsResponse()

        try {
            val url = URL(requestUrl)
            val connection = url.openConnection() as HttpURLConnection
            when (connection.responseCode) {
                HttpsURLConnection.HTTP_OK -> {
                    val br = BufferedReader(
                        InputStreamReader(
                            connection.inputStream,
                            Charset.forName("UTF-8")
                        )
                    )
                    var line: String?

                    while (br.readLine().also { line = it } != null) {
                        content.append(line).append("\n")
                    }

                    dataResponse.response = content.toString().ignoreSpecialCharacters()
                }

                else -> {
                    throw Exception("${connection.responseCode}")
                }
            }

        } catch (e: Exception) {
            throw Exception("Something wrong")
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("500")
        } catch (e: Error) {
            e.printStackTrace()
            throw Exception("Something wrong")
        }

        return dataResponse
    }
}