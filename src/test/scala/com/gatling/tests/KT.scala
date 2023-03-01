package com.gatling.tests

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*

class KT extends Simulation{

  val httpProtocol = http.baseUrl(url = "https://reqres.in/api/users")
  val scn = scenario ("Get API Request Demo")
  exec(
      http(requestName = " Get Single User ")
        .get("/2")
        .check(status.is(expected = 200))
        .check(jsonPath("$.data.first_name"))
    )
      .pause(duration = 2)

  setUp(
    scn.inject(rampUsers(10).during(15))
      .protocols(httpProtocol)
  )
}
