import de.htwg.se.kakuro.controller.controllerComponent.controllerImpl.{ Controller, SetCommand }
import de.htwg.se.kakuro.model.fieldComponent.FieldImpl.FieldCreator
import de.htwg.se.kakuro.util.{ Command, Observable, Observer, UndoManager }
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ Matchers, WordSpec }

@RunWith(classOf[JUnitRunner])
class ObservableSpec extends WordSpec with Matchers {
  val observer = new Observable
  val obs = new Observer {
    override def update: Unit = ???
  }
  val obs2 = new Observer {
    override def update: Unit = ???
  }
  "A Observable" when {
    "not set to any value " should {

      "add" in {
        observer.add(obs)
        observer.subscribers.length should be(1)
      }
      "delete" in {
        observer.remove(obs)
        observer.subscribers.length should be(0)
      }
      "notify" in {
        observer.notifyObservers
        true should be(true)
      }
    }
  }
}
