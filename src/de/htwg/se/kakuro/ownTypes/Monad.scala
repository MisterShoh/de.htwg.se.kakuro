package de.htwg.se.kakuro.ownTypes

import de.htwg.se.kakuro.model.fieldComponent.FieldInterface

class ItemMonad[T](val items: Seq[T]) {
  def map(f: T => T): Seq[T] = items.map(item => f(item))
}

trait Option[FieldInterface] {
  def map(f: FieldInterface => FieldInterface): Option[FieldInterface]
}

case class Some[FieldInterface](b: FieldInterface) extends Option[FieldInterface] {
  def map(f: FieldInterface => FieldInterface) = new Some(f(b))
}

case class None[FieldInterface]() extends Option[FieldInterface] {
  def map(f: FieldInterface => FieldInterface) = new None
}