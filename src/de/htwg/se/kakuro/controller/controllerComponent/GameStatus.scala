package controller.controllerComponent

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, RESIZE, SET, NEW, UNDO, SELECTED, REDO, CANDIDATES, SOLVED, NOT_SOLVABLE = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "A new game was created",
    SET -> "A Cell was set",
    RESIZE -> "Game was resized",
    UNDO -> "Undone one step",
    CANDIDATES -> "Showing candidates",
    SELECTED -> "Selected Cell",
    REDO -> "Redone one step",
    SOLVED -> "Game successfully solved",
    NOT_SOLVABLE -> "Game not solvable"
  )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}
