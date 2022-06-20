package com.github.AleksandraAncupova

object Day4Exercise_Collect extends App {
  //TODO write two partial functions
  //getEvenSquare applies to only positive even numbers  -> returns square
  //getOddCube applies to only positive odd numbers -> returns cube
  //combine both partial functions into a single partial function
  //doPositives will work on positive numbers

  //using collect get the new values into
  //TODO
  //val processedNumbers =
  //println the results


  val getEvenSquare = new PartialFunction[Int, Int] {
    def apply(n: Int): Int = n*n
    def isDefinedAt(n: Int) = n > 0 && n%2 == 0
  }

  val getOddCubes = new PartialFunction[Int,Int] {
    def apply(n: Int): Int = n*n*n
    def isDefinedAt(n: Int) = n >0 && n%2 == 1
  }

  val doPositives = getEvenSquare orElse getOddCubes

  val numbers = (-5 to 28).toArray

  val collectedNumbers = numbers.collect(doPositives)
  println(collectedNumbers.sorted.mkString(","))

}
