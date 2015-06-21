package components

import io.strongtyped.active.slick.ActiveSlick
import scala.slick.jdbc.JdbcBackend
import scala.util.Try
import models.Favourite


trait FavouriteExtensions {
  this: ActiveSlick with Schema =>

  import jdbcDriver.simple._

  val Favourites = EntityTableQuery[Favourite, FavouritesTable](tag => new FavouritesTable(tag))

  implicit class FavouritesExtensions(val model: Favourite) extends ActiveRecord[Favourite] {

    override def table = Favourites
  }

}