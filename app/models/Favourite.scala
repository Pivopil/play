package models

import io.strongtyped.active.slick.models.Identifiable

case class Favourite(name: String, id: Option[Int] = None)
  extends Identifiable[Favourite] {

  override type Id = Int
  override def withId(id: Id): Favourite = copy(id = Option(id))
}