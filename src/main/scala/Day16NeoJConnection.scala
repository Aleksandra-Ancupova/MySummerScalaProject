package com.github.AleksandraAncupova

import org.neo4j.driver.Values.parameters
import org.neo4j.driver.{AuthTokens, Config, GraphDatabase}

import scala.collection.mutable.ArrayBuffer


case class Movie(id: Int, title: String, released: String, tagline: String)

object Day16NeoJConnection extends App {
  println("Connecting to Neo4j")

  val noSSl = Config.builder().build()

  val pw = "mapw"
  val user = "neo4j"
  val uri = "neo4j+s://be420852.databases.neo4j.io"
  val db = "Movies"

  val driver = GraphDatabase.driver(uri, AuthTokens.basic(user,pw), noSSl)
  println("Opening session")
  val session = driver.session

  val cypherQuery = "MATCH (m:Movie)" + "RETURN m as movie, id(m) as id"
  val result = session.run(cypherQuery, parameters())

  val arrayBuffer = ArrayBuffer[String]()
  while (result.hasNext) {
    val record = result.next
    val movie = record.get("movie")
    arrayBuffer += s"${movie.get("title")} - ${movie.get("released")} : ${movie.get("tagline")}"
  }

//  val resultBuffer = ArrayBuffer[Movie]()
//  while (result.hasNext) {
//    val record = result.next
//    val movie = record.get("movie")
//    resultBuffer += Movie(movie.get("id"), movie.get("title"), movie.get("released"), movie.get("tagline"))
//    resultBuffer.toArray
//  }


  println("Closing session")
  session.close()

  val movies = arrayBuffer.toArray
  println(s"we fot ${movies.length} movies")
  movies.take(5).foreach(println)
}
