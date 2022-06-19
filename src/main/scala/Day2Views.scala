package com.github.AleksandraAncupova

object Day2Views {

  def main(args:Array[String]): Unit = {
    println("Creating temporary views")

    val numbers = (0 to 12).toArray
    println(numbers.mkString(","))

    val squares = numbers.map(n => n*n)
    println(squares.mkString(","))

    val evenSquares = squares.filter(n => n%2 == 0)
    println(evenSquares.mkString(","))

    val firstEvenSquares = numbers.map(n => n*n).filter(n => n%2 == 0).take(3)
    println(firstEvenSquares.mkString(","))

    // slight problem with the above is that single line created a new data collection in memory,
    // so it's not good for large data sets

    // instead we can do views

    val oddCubes = numbers.view.map(n => n*n*n).filter(n => n%2 == 1).slice(0,5).takeRight(3).toArray
    println(oddCubes.mkString(","))

    // queries
    val firstOver10 = numbers.find(n => n > 10)
    println(firstOver10.getOrElse(-1))

    val result = numbers.find(n => n < 0).getOrElse(-1)  // not the best example
    println(result)

    println(numbers.contains(3))


    // val myCombinations = evenSquares.combinations(2).toArray // so we have 2D array

    val myCombinations = (1 to 5).combinations(2).toArray // so we have 2D array
    for (comb <- myCombinations) {
      println(comb.mkString(","))
    }

    // last of the 3 big funtions, map, filter and reduce

    def add (accumulator: Int, currentVal: Int): Int = {
      val theSum = accumulator + currentVal
      println(s"received $accumulator and $currentVal, their sum is $theSum")
      theSum
    }

    // so with reduce we will have accumulator where we store intermediate results
    println(numbers.reduce(add))
    println("only even sums")

    println(numbers.view.filter(n => n%2 == 0).reduce(add))

    println(numbers.sum) // should be same

    // there should also be reduceRight


    // fold methods are just like reduce, but you enter a starting valie
    println(numbers.view.filter(n => n%2 == 0).fold(100)(add))

   val cumulativeSums = numbers.scan(0)(_+_)
    println(cumulativeSums.mkString(","))


  }
}
