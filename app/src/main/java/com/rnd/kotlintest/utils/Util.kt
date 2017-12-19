package com.rnd.kotlintest.utils
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import java.io.IOException
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException


/**
 * Created by Devrepublic-14 on 10/30/2017.
 */
class Util {
    companion object {
        val WS_URL = "https://api.androidhive.info/volley/person_object.json";
        val URL_FOR_XML = "https://api.androidhive.info/pizza/?format=xml";

        val KEY_ID = "id";
        val KEY_NAME = "name";
        val KEY_COST = "cost";
        val KEY_DESC = "description";
        val info = "This is info"

        fun getMoreInfo(): String {
            return "This is more fun"
        }

        fun myToast(context: Context, message: String): Unit = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        fun mapToQueryString(map: Map<String, String>): String {
            val string = StringBuilder()
            if (map.size > 0) {
                string.append("?")
            }
            for ((key, value) in map) {
                string.append(key)
                string.append("=")
                string.append(value)
                string.append("&")
            }
            return string.toString()
        }

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null
        }



    }


    fun getDomElement(xml: String): Document? {
        var doc: Document? = null
        val dbf = DocumentBuilderFactory.newInstance()
        try {

            val db = dbf.newDocumentBuilder()
            val input = InputSource()
            input.setCharacterStream(StringReader(xml))
            doc = db.parse(input)

        } catch (e: ParserConfigurationException) {
            Log.e("Error: ", e.message)
            return null
        } catch (e: SAXException) {
            Log.e("Error: ", e.message)
            return null
        } catch (e: IOException) {
            Log.e("Error: ", e.message)
            return null
        }
        // return DOM
        return doc
    }

    fun getValue(item: Element, str: String): String {
        val n = item.getElementsByTagName(str)
        return this.getElementValue(n.item(0))
     }

    fun getElementValue(elem: Node?): String {
        var child: Node?
        if (elem != null) {
            if (elem!!.hasChildNodes()) {
                child = elem!!.getFirstChild()
                while (child != null) {
                    if (child!!.getNodeType() === Node.TEXT_NODE) {
                        return child!!.getNodeValue()
                     }
                    child = child!!.getNextSibling()
                }
            }
        }
        return ""
    }

}