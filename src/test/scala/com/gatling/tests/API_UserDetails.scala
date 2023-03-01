package com.gatling.tests

import com.typesafe.config._
import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*

import scala.concurrent.duration.*
import scala.io
import scala.util.Using


class API_UserDetails extends Simulation {

  val conf = ConfigFactory.load()

  val httpProtocol = http.baseUrl(url = conf.getString("GetUser"))

  val scn1 = scenario(scenarioName = "Get User Details")
    .exec(
      http(requestName = "Get User Details")

        .get("/posts")
        .header("Authorization",conf.getString("AuthorizationHeader"))
//        .header("Authorization", "Bearer 3cc7d35f11cb454d59110f43ee5056b89e294ffbc583b852af5d9053dea0b259")
        .queryParam("id", "15888")
        .queryParam("user_id", "203504")
        .queryParam("title", "Excepturi allatus aetas illo territo vitium artificiose soleo confero auxilium ambitus.")
        .queryParam("body", "Denique defaeco audax. Despirmatio ver adhuc. Magnam vester tamen. Ipsam vapulus cometes. Terebro asporto speculum. Comminor commodo defigo. Spero conservo varius. Condico decretum adipiscor. Et copia creo. Cilicium sonitus assentator. Vulgaris celer nihil. Nihil ab celebrer. Combibo ars deprecator. Vicissitudo magnam acerbitas. Corrumpo conculco quia. Theatrum cuius claudeo. Tracto vesper addo. Tracto taceo paulatim.")
        .body(StringBody(conf.getString("body12")))
        .check(
          status is 200
        )
    )
//  setUp(scn1.inject(atOnceUsers(1))).protocols(httpProtocol)

  setUp(
    scn1.inject(
      rampConcurrentUsers(0).to(10).during(10.seconds),
      constantConcurrentUsers(10).during(1.minutes),
      rampConcurrentUsers(10).to(0).during(20.seconds)
    )
  )

}
