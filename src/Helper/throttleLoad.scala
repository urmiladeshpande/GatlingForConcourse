package Helper

import io.gatling.core.Predef.{Simulation, nothingFor, *}
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.*
import io.gatling.jdbc.Predef.*

import scala.concurrent.duration.*

class throttleLoad{

   def loadThrottle(scn: ScenarioBuilder) = {
     scn.inject(

       rampUsers(20).during(30.seconds),
       nothingFor(600.seconds),
       rampUsers(10).during(30.seconds),
       nothingFor(1200.seconds),
       rampUsers(10).during(30.seconds),
       nothingFor (1800.seconds),
       rampUsers(10).during(30.seconds),
       nothingFor(2400.seconds),
       rampUsers(10).during(30.seconds),
       nothingFor(3300.seconds),
       rampUsers(10).during(30.seconds),
       nothingFor(4200.seconds),
       rampUsers(10).during(30.seconds),
       nothingFor(5100.seconds),
       rampUsers(10).during(30.seconds),

     )
//     scn.inject(rampUsers(20).during(30.seconds)).throttle(holdFor(7200))
//     scn.inject(nothingFor(600.seconds),rampUsers(10).during(30.seconds)).throttle(holdFor(6600))
  }
}

//
object throttleLoad {

}