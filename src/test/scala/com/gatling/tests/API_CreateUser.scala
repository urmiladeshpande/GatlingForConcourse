package com.gatling.tests


import com.typesafe.config._
import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*

import scala.concurrent.duration.*
import scala.io
import scala.util.Using

class API_CreateUser extends Simulation {

  val conf = ConfigFactory.load()

  val httpProtocol = http .baseUrl(url=conf.getString("CreateUserURL"))

  val scn1 = scenario(scenarioName = "Create a new user")
    .exec(
      http(requestName = "Create a new user request")

        .post("/users")
        .header("Authorization", "Bearer 3cc7d35f11cb454d59110f43ee5056b89e294ffbc583b852af5d9053dea0b259")
        .queryParam("id", "203511")
        .queryParam("name", "en-Chaturvedi")
        .queryParam("email", "chaturvedlei_ret@ondricka.io")
        .queryParam("gender", "female")
        .queryParam("status", "inactive")
        .check(
          status is 201
        )
    )
  setUp(scn1.inject(atOnceUsers(1))).protocols(httpProtocol)

}
