package com.github.AleksandraAncupova

import com.redis.RedisClient

object Day11RedisCommands extends App {
  val port = 19583
  val pw = Some(scala.util.Properties.envOrElse("redisPW", "nothing"))
  val url = "redis-19583.c293.eu-central-1-1.ec2.cloud.redislabs.com"

  val r = new RedisClient(host = url, port, 0, secret = pw)

  val myValues = r.mget("myname", "mycount", "number").getOrElse(List[Option[String]]()).toArray

  for(value <- myValues) {
    println(s"value -> ${value.getOrElse("")}")
  }

  val msetResults = r.mset(("weather", "sunny"), ("temperature", 25), ("berry", "strawberries"))
 println(s"Mset worked? : $msetResults")

  val keys = r.keys().getOrElse(List[String]())
  keys.foreach(key => println(s"Key $key type us ${key.getClass}"))

  // so we set key user:42, it will have hash fields
  r.hmset("user:42", Array(("name", "Valdis"), ("likes", "potatoes"),("color", "green"), ("parkingTickets", 3)))

  val myName = r.hget("user:42", "name").getOrElse("")

  val parkingTickets = r.hget("user:42", "parkingTickets").getOrElse("0").toInt
  println(s"$myName has got $parkingTickets parking tickets")

  r.hincrby("user:42", "parkingTickets", 10)


  // sorted Sets

  r.zadd("hackers", 1940, "Alan Kay")
  r.zadd("hackers", 1957, "Sophie Wilson")
  r.zadd("hackers", 1912, "Alan Turing")
  r.zadd("hackers", 1969, "Linus Torvalds")

  val hackers = r.zrange("hackers", 0, -1).getOrElse(List[String]())
  println(hackers.mkString(","))

  // TODO 3 more hackers with their scores/birthyear
  // TODO get all hackers born after 1960 - zrangebyscore

  r.zadd("hackers", 1949, "Anita Borg")
  r.zadd("hackers", 1965, "Yukihiro Matsumoto")
  r.zadd("hackers", 1916, "Claude Shannon")

  val hackers1960 = r.zrangebyscore("hackers", 1960,true, 2000, true, Option(0,100)).getOrElse(List[String]())
  println(hackers1960.mkString(","))

  // TODO create new hash key with at least 5 fiels with corresponding values
  // TODO retrieve 3 of those values - use hget

  r.hmset("user:55", Array(("name", "Alex"), ("likes", "brownies"),("dislikes", "cereal"), ("workoutsCompleted", 3)))

  val userName = r.hget("user:55", "name").getOrElse("")
  val myLikes = r.hget("user:55", "likes").getOrElse("")
  println(s"User $userName likes $myLikes")


}
