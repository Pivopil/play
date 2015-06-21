
package models

import io.strongtyped.active.slick.models.Identifiable

case class Movie(themoviedbId: Int, favouriteId: Int, name: String, id: Option[Int] = None)
  extends Identifiable[Movie] {

  override type Id = Int

  override def withId(id: Id): Movie = copy(id = Option(id))
}

