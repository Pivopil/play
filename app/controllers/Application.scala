package controllers

import play.api.Play
import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {
  def index = Action {
    Ok(views.html.index())
  }

  def api() = Action {
    val apiMap = Map(
      "apiKey"-> Play.current.configuration.getString("themoviedb.apiKey").getOrElse(""),
      "themoviedbUrl" -> "http://api.themoviedb.org/3/search/movie?api_key=__apiKey__&query=__query__&page=__page__&language=en",
      "themoviedbDetailsUrl" -> "https://api.themoviedb.org/3/movie/__themoviedbId__?api_key=__apiKey__",
      "baseUrl" -> "api",
      "favouriteListUrl" -> "list",
      "movieUrl" -> "movie"
    )

    Ok(Json.toJson(apiMap)).as(JSON)
  }
}
