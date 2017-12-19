package com.rnd.kotlintest.activities

import android.os.Bundle
import android.widget.Toast
import com.rnd.kotlintest.R
import com.rnd.kotlintest.adapters.ListViewAdapter
import com.rnd.kotlintest.controller.APIController
import com.rnd.kotlintest.models.PizzaModel
import com.rnd.kotlintest.services.ServiceVolley
import com.rnd.kotlintest.utils.Util
import kotlinx.android.synthetic.main.activity_xml_parsing.*
import org.w3c.dom.CharacterData
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Created by Devrepublic-14 on 12/14/2017.
 */
class XmlParsingActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_parsing)
        try {
            initCoponets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initCoponets() {
        super.initCoponets()
        btnClick.setOnClickListener {
            if (Util.isNetworkAvailable(this)) {
                callingWsHere()
             } else {
                Toast.makeText(this, "Network NA", Toast.LENGTH_SHORT).show()
             }
         }

    }

    var pizzaModels = ArrayList<PizzaModel>()

    private fun callingWsHere() {
        val service = ServiceVolley()
        val apiController = APIController(service)
        apiController.getStringResult(Util.URL_FOR_XML, "") { response ->
            // Parse the result
            try {
                val db = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                val input = InputSource()
                input.setCharacterStream(StringReader(response))
                val doc = db.parse(input)
                var nodes: NodeList = doc.getElementsByTagName("item")
                pizzaModels.clear()
                for (i in 0..nodes.length) {
                    var nodeId: NodeList = doc.getElementsByTagName("id")
                    var nodeName: NodeList = doc.getElementsByTagName("name")
                    var nodeCost: NodeList = doc.getElementsByTagName("cost")
                    var nodeDiscriptions: NodeList = doc.getElementsByTagName("description")
                    val id = nodeId.item(i) as Element
                    val name = nodeName.item(i) as Element
                    val cost = nodeCost.item(i) as Element
                    val discription = nodeDiscriptions.item(i) as Element
                    val str: String =
                            getCharacterDataFromElement(id) +
                                    getCharacterDataFromElement(name) +
                                    getCharacterDataFromElement(cost) +
                                    getCharacterDataFromElement(discription)
                    var pizzamodel: PizzaModel = PizzaModel(getCharacterDataFromElement(id),
                            getCharacterDataFromElement(name),
                            getCharacterDataFromElement(cost),
                            getCharacterDataFromElement(discription))
                    pizzaModels.add(pizzamodel)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        var listViewAdapter: ListViewAdapter = ListViewAdapter(this, pizzaModels)
        lvPizza.adapter = listViewAdapter

    }

    fun getCharacterDataFromElement(e: Element): String {
        val child = e.firstChild
        if (child is CharacterData) {
            val cd = child as CharacterData
            return cd.getData()
        }
        return ""
    }


}