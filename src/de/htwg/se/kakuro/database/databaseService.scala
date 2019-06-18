package de.htwg.se.kakuro.database

import java.io.FileInputStream

import scala.io.Source
import play.api.libs.json._

import scala.concurrent.Future
import com.ibm.couchdb._

//import upickle.Invalid.Json
// Fake it till you make it

class databaseService {

  def getFieldfromFile(): JsValue = {
    val filename = "grid.json"
    val stream = new FileInputStream(filename)
    val json = try { Json.parse(stream) } finally { stream.close() }
    return json
  }
  def getField(): String = {
    val jsDatei = getFieldfromFile()
    val couch = CouchDb("127.0.0.1", 5984)
    var res = couch.server
    val kakuro_db = couch.db("kakuro", TypeMapping.empty)
    val test = kakuro_db
    return jsDatei.toString
  }
}
