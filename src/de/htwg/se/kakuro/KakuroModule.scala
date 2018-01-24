package de.htwg.se.kakuro

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.{ Cell, Field, Matrix, Sum }
import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, FieldInterface, MatrixInterface, SumInterface }
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import de.htwg.se.kakuro.model.fileIOComponent.fileIOJsonImpl.FileIO

class KakuroModule extends AbstractModule with ScalaModule {

  def configure() = {
    //bind[FieldInterface].to[Field]
    //bind[ControllerInterface].to[Controller]
    //bind[SumInterface].to[Sum]

    //bind[MatrixInterface[CellInterface]].to[Matrix[Cell]]
    bind[FileIOInterface].to[FileIO]

  }

}
