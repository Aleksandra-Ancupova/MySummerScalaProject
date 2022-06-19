package com.github.AleksandraAncupova

import scala.io.StdIn.readLine

object Day2Exercise_ViewMapFilterReduce extends App {
  val input = readLine("Please enter a number:").toInt

  val numbers = (1 to input).toArray

  def mult(accumulator: Int, currentVal: Int): Int = {
    val theProduct = accumulator * currentVal
    println(s"received $accumulator and $currentVal, their product is $theProduct")
    theProduct
  }

  val result = numbers.view.map(n => n*n).filter(n => n%2 == 1).reduce(mult)
  println(result)

  // val result = numbers.view.map(n => n*n).filter(n => n%2 == 1).reduce(_*_)

  val hugeResult = numbers.view.map(n => BigInt(n*n)).filter(_%2 == 1).reduce(_*_)
  println(s"Also a truly huge result: $hugeResult")




}
