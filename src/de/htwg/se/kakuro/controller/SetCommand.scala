package controller

import de.htwg.se.kakuro.controller.Controller
import de.htwg.se.kakuro.util.Command

class SetCommand(row: Int, col: Int, value: Int, controller: Controller) extends Command {
  override def doStep: Unit = controller.field = controller.field.set(row, col, value)

  override def undoStep: Unit = controller.field = controller.field.set(row, col, 0)

  override def redoStep: Unit = controller.field = controller.field.set(row, col, value)
}
