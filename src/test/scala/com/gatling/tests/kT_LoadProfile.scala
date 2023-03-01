package com.gatling.tests

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*

import scala.concurrent.duration.*

class KT_loadProfile extends Simulation{

  val httpProtocol = http.baseUrl(url = "https://reqres.in/api/users")

  val scn = scenario ("Get API Request Demo")
    .exec(http("/2")
      .get("/2")
      .check(status.is(expected = 200))
      .check(jsonPath("$.data.first_name"))
    )
    .pause(duration = 2)
  setUp(
    scn.inject(
//        rampConcurrentUsers(0).to(10).during(10.seconds),
//        constantConcurrentUsers(10).during(1.minutes),
//        rampConcurrentUsers(10).to(0).during(20.seconds)
      incrementConcurrentUsers(10)
        .times(5)
        .eachLevelLasting(5)
        .separatedByRampsLasting(60)
        .startingFrom(10) // Int
    ).protocols(httpProtocol)
  )
}
