

package controllers

import components.ActiveSlickCake.cake._
import io.strongtyped.active.slick.exceptions.RowNotFoundException
import models.Movie
import play.api.db.slick.{DBAction, _}
import play.api.libs.json.{Writes, JsError, JsSuccess, Json}
import play.api.mvc._

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}
import play.api.mvc._
import play.api.libs.json.Json

object MovieController extends Controller {

  implicit val movieFormat = Json.format[Movie]

  def list(listId: Int) = DBAction { implicit rs =>
    val allMovies = Movies.fetchAll.filter(_.favouriteId == listId)
    Ok(Json.toJson(allMovies))
  }

  def get(id: Int, listId: Int) = DBAction { implicit rs =>
    Movies.tryFindById(id) match {
      case Success(movie) => Ok(Json.toJson(movie))
      case Failure(RowNotFoundException(_)) => NotFound(s"No Movie found for $id")
      case Failure(exp) => BadRequest(s"Can't find movie for $id: ${exp.getMessage}")
    }
  }

  def create(listId: Int) = DBAction.transaction(parse.json) { implicit rs =>
    val movieResult = Json.fromJson[Movie](rs.request.body)

    val tryCreate = Try(movieResult.get).flatMap { movie =>
      movie.trySave
    }

    tryCreate match {
      case Success(movie) => Created(Json.toJson(movie)).withHeaders("Location" -> s"/movie/${movie.id.get}")
      case Failure(exp) => BadRequest(s"Can't create movie: ${exp.getMessage}")
    }

  }

  def update(id: Int, listId: Int) = DBAction.transaction(parse.json) { implicit rs =>
    val movieResult = Json.fromJson[Movie](rs.request.body)

    val tryUpdate = Try(movieResult.get).flatMap { movie =>
      movie.tryUpdate
    }

    tryUpdate match {
      case Success(movie) => Ok(Json.toJson(movie))
      case Failure(RowNotFoundException(_)) => NotFound(s"No Movie found for $id")
      case Failure(exp) => BadRequest(s"Can't update movie: ${exp.getMessage}")
    }
  }

  def delete(id: Int, listId: Int) = DBAction.transaction { implicit rs =>
    // DELETE is idempotent,
    // but we make sure that no exception is thrown
    Movies.tryFindById(id).flatMap { movie =>
      movie.tryDelete
    }

    NoContent
  }

}