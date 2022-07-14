package com.github.AleksandraAncupova

import CassandraExample.{getClusterSession, rawQuery, runQuery}

object Day15CassandraCRUD extends App {
  val host = "cassandra-34b8083c-alexandra-ed32.aivencloud.com"
  val port = 23071
  val username = "avnadmin"
  val pw = "AVNS_cbcNoBBuR2jeAmwpeC8"
  val caPath = "./src/resources/certifications/ca.pem"

  val (cluster, session) = getClusterSession(host=host,port=port,username=username,password=pw, caPath=caPath)

  val keyspaceCreationQuery =
    """
      |CREATE KEYSPACE IF NOT EXISTS
      |vs_keyspace WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'aiven' : 3 };
      |""".stripMargin

  rawQuery(session, keyspaceCreationQuery)

  val tableCreationQuery =
    """
      |CREATE TABLE IF NOT EXISTS users_by_country (
      |    country text,
      |    user_email text,
      |    first_name text,
      |    last_name text,
      |    age int,
      |    PRIMARY KEY ((country), user_email)
      |)
      |""".stripMargin

  runQuery(session, "vs_keyspace", tableCreationQuery)


  //if column is regular int then we give it regular int of course
  session.execute("INSERT INTO vs_keyspace.users_by_country (country,user_email,first_name,last_name,age)" +
    " VALUES (?,?,?,?,?)", "US", "john@example.com", "John","Wick",55)
  session.execute("INSERT INTO vs_keyspace.users_by_country (country,user_email,first_name,last_name,age)" +
    " VALUES (?,?,?,?,?)", "UK", "mrbean@example.com", "Rowan","Atkinson",65)
  session.execute("INSERT INTO vs_keyspace.users_by_country (country,user_email,first_name,last_name,age)" +
    " VALUES (?,?,?,?,?)", "UK", "boris@example.com", "Boris","Johnson",58)
  session.execute("INSERT INTO vs_keyspace.users_by_country (country,user_email,first_name,last_name,age)" +
    " VALUES (?,?,?,?,?)", "LV", "kk@example.com", "Krišjānis","Kariņš",60)

  val userResults = session.execute("SELECT * FROM vs_keyspace.users_by_country")
  userResults.forEach(row => println(row))


  //TODO add 2 more users, one from LV, one from LT
  //return Latvian users
  //return Lithuanian users

  session.execute("INSERT INTO vs_keyspace.users_by_country (country,user_email,first_name,last_name,age)" +
    " VALUES (?,?,?,?,?)", "LV", "as@example.com", "Anastasija","Sevastova",32)
  session.execute("INSERT INTO vs_keyspace.users_by_country (country,user_email,first_name,last_name,age)" +
    " VALUES (?,?,?,?,?)", "LT", "ls@example.com", "Lina","Stančiūtė",36)

  println("Printing LV users:")
  val userResultsLV = session.execute("SELECT * FROM vs_keyspace.users_by_country WHERE country = 'LV'")
  userResultsLV.forEach(row => println(row))

  println("Printing LT users:")
  val userResultsLT = session.execute("SELECT * FROM vs_keyspace.users_by_country WHERE country = 'LT'")
  userResultsLT.forEach(row => println(row))


  cluster.close()
}
