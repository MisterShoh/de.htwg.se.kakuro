//package de.htwg.se.kakuro.model.fieldComponent

import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator

object ModelWorksheet {

	val fieldx:FieldInterface =(new FieldCreator).createNewField(8)
	println(fieldx)
}