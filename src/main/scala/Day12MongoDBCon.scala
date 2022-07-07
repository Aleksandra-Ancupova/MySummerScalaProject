package com.github.AleksandraAncupova

import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.{Document, MongoClient, MongoDatabase}

import java.lang.Thread.sleep

object Day12MongoDBCon extends App {
  println("Connecting to MongoDB")

  val userName = scala.util.Properties.envOrElse("MongoDBuser", "nothing")
  val pw = scala.util.Properties.envOrElse("MongoDBpw", "nothing")

  val uri: String = s"mongodb+srv://$userName:$pw@cluster0.kwor7.mongodb.net/?retryWrites=true&w=majority"
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("sample_restaurants")

  val collectionName = "restaurants"
  val collection = db.getCollection(collectionName)

  // so analogous to SELECT * FROM restaurants WHERE name = "Carvel Ice Cream"
  val carverIceCream = collection.find(equal("name", "Carvel Ice Cream"))
    .subscribe((doc: Document) => println(doc.toJson()),
      (e: Throwable) => println(s"Query error : $e"),
      () => println("Runs after query is complete"))


  sleep(5000)

  // we need to close the client

  client.close()
}
