package de.htwg.se.kakuro.model.fieldComponent.fieldDIComponent

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ FieldCreator, Field => NotInjectedField }

class Field @Inject() (@Named("DefaultSize") size: Int) extends NotInjectedField(size) {

  def createNewField: FieldInterface = (new FieldCreator).createNewField(size)

}