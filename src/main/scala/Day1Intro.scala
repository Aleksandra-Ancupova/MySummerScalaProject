package com.github.AleksandraAncupova

object Day1Intro extends App {
  println("Hello, Scala!!!")

  val strings = Array("Valdis", "alus", "aldaris", "Bauska", "Valdis")
  val queries = Array("Valdis", "Bauska", "Cēsis")

  val result = Array(1, 2, 0)

  def getStringMatches(strings: Array[String], queries: Array[String]): Array[Int] = {
    // val arrayOfMatches = queries.map(qry => strings.count(entry => entry == qry))
     // Array(1,2,0) //FIXME
    queries.map(qry => strings.count(_ == qry))
  }


  val results = getStringMatches(strings, queries)

  println(results.mkString(","))

  // big idea of effective mapping

  // like slice
  val grouped = strings.grouped(3).toArray  // not what we want

  val groupedBy = strings.groupBy(_.hashCode)
  groupedBy.foreach(ek => println(ek))


  /**
   * TOO long a function name, need to stick to 3 workds max
   * @param strings
   * @param queries
   * @return
   */
  def getStringMatchesOptimizedForLargeQueries(strings: Array[String], queries: Array[String]): Array[Int] = {
    // initialize mutable map
    val mutableMap = collection.mutable.Map[String, Int]()
    for (text <- strings) {
      if (mutableMap.contains(text)) mutableMap(text) += 1
      else mutableMap(text) = 1
    }
    val solution = queries.map(qry => mutableMap.getOrElse(qry, 0))
//    for ((text, cnt) <- mutableMap) {
//      println(s"$text occurs $cnt times")
  solution
  }


  // so using gtOrElse e can provide a default value
  // println(mutableMap.getOrElse("Valdis", "not found"))


 val solution = getStringMatchesOptimizedForLargeQueries(strings, queries)

  println(solution.mkString(","))

}
