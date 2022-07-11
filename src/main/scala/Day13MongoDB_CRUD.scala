package com.github.AleksandraAncupova

import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Filters.{and, equal, or}
import org.mongodb.scala.{Document, MongoClient, MongoDatabase}

import scala.collection.mutable.ArrayBuffer

object Day13MongoDB_CRUD extends App {

  val userName = scala.util.Properties.envOrElse("MongoDBuser", "nothing")
  val pw = scala.util.Properties.envOrElse("MongoDBpw", "nothing")

  val uri: String = s"mongodb+srv://$userName:$pw@cluster0.kwor7.mongodb.net/?retryWrites=true&w=majority"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("sample_restaurants")

  val collectionName = "restaurants"
  val collection = db.getCollection(collectionName)

  val resultsBuffer = ArrayBuffer[Document]()

  val firstRestaurant = collection.find().first()
  println(firstRestaurant)

//  val allRestaurants = collection.find()
//    .subscribe(
//      (doc: Document) => {
//    resultsBuffer += doc
//  },
//  (e: Throwable) => println(s"Query error : $e"),
//      afterQuerySuccess
//  )

 println("Query is still running")



  // success query here
  def afterQuerySuccess(): Unit = {
    println("Closing after last query")
    val allRestaurantsDocs = resultsBuffer.toArray
    println(s"We got ${allRestaurantsDocs.length} restaurants total")
    println(allRestaurantsDocs.head.toJson())
    val savePath = "src/resources/json/restaurants.json"
    val restaurantJSON = allRestaurantsDocs.map(_.toJson())
    Util.saveLines(savePath, restaurantJSON)
    client.close()
  }

  //TODO find ALL restaurants in Manhattan offering barbeque OR BBQ  in name (maybe try cuisine as well)
  val allRestaurants = collection.find(and(equal("borough", "Manhattan"),
    or(Filters.regex("name", ".*Barbeque.*"),
    Filters.regex("name", ".*BBQ.*"), equal("cuisine", "Barbecue"))))
      .subscribe(
        (doc: Document) => {
      resultsBuffer += doc
    },
    (e: Throwable) => println(s"Query error : $e"),
        afterQuerySuccess
    )



}
