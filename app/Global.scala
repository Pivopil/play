import play.api._
import play.api.Play.current
import components.ActiveSlickCake.cake._
import models.{Movie, Favourite}

object Global extends GlobalSettings {

  override def onStart(app: Application): Unit = {
    super.onStart(app)
    play.api.db.slick.DB.withTransaction { implicit session =>
      // adding some Favourites
//      val favourites = Favourite("Sci Fi") :: Favourite("Comedy") :: Favourite("Thriller") :: Nil
//      favourites.foreach(_.save)
//      val movies = Movie(597, 1, "Titanic") :: Movie(6479, 2, "I Am Legend") :: Nil
//      movies.foreach(_.save)
    }
  }
}