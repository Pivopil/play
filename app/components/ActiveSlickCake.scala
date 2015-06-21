package components

import play.api.db.slick.Config
import io.strongtyped.active.slick.ActiveSlick
import scala.slick.driver.JdbcDriver

class ActiveSlickCake(override val jdbcDriver: JdbcDriver)
  extends ActiveSlick with Schema with FavouriteExtensions with MovieExtensions {

  import jdbcDriver.simple._

  def createSchema(implicit sess: Session) = {
      Seq(Favourites, Movies).map(_.ddl).reduce(_ ++ _).create
  }

}

object ActiveSlickCake {
  val cake = new ActiveSlickCake(Config.driver)
}