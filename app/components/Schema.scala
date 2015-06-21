package components

import io.strongtyped.active.slick.{TableQueries, Tables, Profile}
import models.{Favourite, Movie}


trait Schema {
  this: Tables with TableQueries with Profile =>

  import jdbcDriver.simple._


  class FavouritesTable(tag: Tag) extends EntityTable[Favourite](tag, "FAVOURITE") {

    def name = column[String]("FAVOURITE_NAME")

    def id = column[Int]("FAVOURITE_ID", O.PrimaryKey, O.AutoInc)

    def * = (name, id.?) <>(Favourite.tupled, Favourite.unapply)
  }

  lazy val favouritesTable = TableQuery[FavouritesTable]

  class MoviesTable(tag: Tag) extends EntityTable[Movie](tag, "MOVIE") {

    def themoviedbId = column[Int]("THE_MOVIE_DB_ID")

    def favouriteId = column[Int]("FAVOURITE_ID")

    def name = column[String]("MOVIE_NAME")

    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def * = (themoviedbId, favouriteId, name, id.?) <>(Movie.tupled, Movie.unapply)

    def favourites = foreignKey("FAVOURITE_ID", favouriteId, favouritesTable)(_.id)

  }

  lazy val moviesTable = TableQuery[MoviesTable]

}
