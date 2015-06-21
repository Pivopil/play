package components

import io.strongtyped.active.slick.ActiveSlick
import models.Movie


trait MovieExtensions {
  this: ActiveSlick with Schema =>

  import jdbcDriver.simple._

  val Movies = EntityTableQuery[Movie, MoviesTable](tag => new MoviesTable(tag))

  implicit class MoviesExtensions(val model: Movie) extends ActiveRecord[Movie] {

    override def table = Movies
  }
}
