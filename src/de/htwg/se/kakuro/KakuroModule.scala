package de.htwg.se.kakuro

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.kakuro.controller.controllerComponent.ControllerInterface

import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.kakuro.model.fieldComponent.fieldDIComponent.Field
import de.htwg.se.kakuro.model.fieldComponent.FieldInterface
import de.htwg.se.kakuro.model.fileIOComponent.FileIOInterface
import de.htwg.se.kakuro.model.fileIOComponent._

class KakuroModule extends AbstractModule with ScalaModule {
  val defaultSize: Int = 8
  def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[FieldInterface].to[Field]
    bind[ControllerInterface].to[Controller]
    bind[FileIOInterface].to[fileIOJsonImpl.FileIO]
  }

}
