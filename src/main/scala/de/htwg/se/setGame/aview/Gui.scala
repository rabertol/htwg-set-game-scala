package de.htwg.se.setGame

import java.awt.Toolkit

import de.htwg.se.setGame.aview.gui.ImageIconGenerator
import de.htwg.se.setGame.model.Card

import scala.swing._
import scala.swing.event.ButtonClicked

/**
  * @author Philipp Daniels
  */
class Gui(private val controller: Controller) extends MainFrame {
  listenTo(controller)
  title = Gui.Title
  visible = true

  preferredSize = Toolkit.getDefaultToolkit.getScreenSize
  private val label = new Label {text = "Hello World"

  }

  contents = new FlowPanel {
    contents += label
    // contents += Button("Press me, please") { println("Thank you") }
  }

  override def closeOperation(): Unit = {
    controller.exitApplication()
  }

  reactions += {
    case e: ExitApplication => exit()
  }

  private def exit(): Unit = {
    close()
    dispose()
  }
}

object Gui {
  val Title = "SetGame"
  def apply(controller: Controller): Gui = new Gui(controller)
}