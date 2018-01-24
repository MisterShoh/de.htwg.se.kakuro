package de.htwg.se.kakuro.controller.controllerComponent

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, NEW, SET, SELECTED, UNDO, REDO, LOADED, COULDNOTLOAD, SAVED = Value
  //CANDIDATES, SOLVED, NOT_SOLVABLE, RESIZE

  val map: Map[GameStatus, String] = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "A new game was created",
    SET -> "A Cell was set",
    SELECTED -> "Selected Cell",
    UNDO -> "Undone one step",
    REDO -> "Redone one step",
    LOADED -> "A new Game was loaded",
    COULDNOTLOAD -> "The file could not be loaded",
    SAVED -> "The Game was saved"
  )
  //RESIZE -> "Game was resized",
  //CANDIDATES -> "Showing candidates",
  //SOLVED -> "Game successfully solved",
  //NOT_SOLVABLE -> "Game not solvable"

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}
