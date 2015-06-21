package controllers

import components.ActiveSlickCake.cake._
import io.strongtyped.active.slick.exceptions.RowNotFoundException
import models.Favourite
import play.api.db.slick.{DBAction, _}
import play.api.libs.json.{Writes, JsError, JsSuccess, Json}
import play.api.mvc._

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}
import play.api.mvc._
import play.api.libs.json.Json

object FavouriteController extends Controller {

  implicit val favouriteFormat = Json.format[Favourite]


  def list = DBAction { implicit rs =>
    val allFavourites = Favourites.fetchAll
    Ok(Json.toJson(allFavourites))
  }

  def get(id: Int) = DBAction { implicit rs =>
    Favourites.tryFindById(id) match {
      case Success(favourite) => Ok(Json.toJson(favourite))
      case Failure(RowNotFoundException(_)) => NotFound(s"No Favourites found for $id")
      case Failure(exp) => BadRequest(s"Can't find favourite for $id: ${exp.getMessage}")
    }
  }

  def create = DBAction.transaction(parse.json) { implicit rs =>
    val favouriteResult = Json.fromJson[Favourite](rs.request.body)

    val tryCreate = Try(favouriteResult.get).flatMap { favourite =>
      favourite.trySave
    }

    tryCreate match {
      case Success(favourite) => Created(Json.toJson(favourite)).withHeaders("Location" -> s"/favourite/${favourite.id.get}")
      case Failure(exp) => BadRequest(s"Can't create favourite: ${exp.getMessage}")
    }

  }

  def update(id: Int) = DBAction.transaction(parse.json) { implicit rs =>
    val favouriteResult = Json.fromJson[Favourite](rs.request.body)

    val tryUpdate = Try(favouriteResult.get).flatMap { favourite =>
      favourite.tryUpdate
    }

    tryUpdate match {
      case Success(favourite) => Ok(Json.toJson(favourite))
      case Failure(RowNotFoundException(_)) => NotFound(s"No Favourites found for $id")
      case Failure(exp) => BadRequest(s"Can't update favourite: ${exp.getMessage}")
    }
  }

  def delete(id: Int) = DBAction.transaction { implicit rs =>
    // DELETE is idempotent,
    // but we make sure that no exception is thrown
    Favourites.tryFindById(id).flatMap { favourite =>
      favourite.tryDelete
    }

    NoContent
  }

}