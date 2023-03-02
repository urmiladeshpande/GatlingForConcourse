package com.gatling.tests

import Helper.throttleLoad

import scala.concurrent.duration._
import io.gatling.core.Predef.{holdFor, _}
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ComputerDatabase  extends Simulation{



	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
//		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-IN,en-US;q=0.9,en-GB;q=0.8,en;q=0.7")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")


	val scn = scenario("ComputerDatabase")
		.exec(http("request_0")
			.get("/computers?f=Sample+Comp")
			)
	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
//	setUp( new throttleLoad().loadThrottle(scn))
}