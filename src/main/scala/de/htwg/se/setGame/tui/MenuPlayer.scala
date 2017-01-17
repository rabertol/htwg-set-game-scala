package de.htwg.se.setGame.tui

import com.typesafe.scalalogging.Logger
import de.htwg.se.setGame.{CancelAddPlayer, Controller, ExitApplication, PlayerAdded}

/**
  * @author Philipp Daniels
  */
class MenuPlayer(private val controller: Controller, private val playerName: Menu) extends Menu {

  private val logger = Logger(getClass)
  private var menuPlayerName = new MenuPlayerName(controller)
  listenTo(controller)

  getActions(MenuPlayer.PlayerCommand) = (new Add, MenuPlayer.PlayerDescription)
  getActions(MenuPlayer.CancelCommand) = (new Cancel, MenuPlayer.CancelDescription)
  getActions(MenuPlayer.ExitCommand) = (new Exit, MenuPlayer.ExitDescription)

  reactions += {
    case e: ExitApplication => exit()
    case e: CancelAddPlayer => exit()
    case e: PlayerAdded => logger.info(MenuPlayer.PlayerAdded)
  }

  protected override def preMenuList(): Unit = {
    super.preMenuList()
    logger.info(MenuPlayer.MenuHeading)
  }

  private class Add extends Action {
    override def execute(): Unit = playerName.process(); outputMenuList()
  }

  private class Cancel extends Action {
    override def execute(): Unit = controller.cancelAddPlayer()
  }

  private class Exit extends Action {
    override def execute(): Unit = controller.exitApplication()
  }
}

object MenuPlayer {
  val ExitCommand = "x"
  val ExitDescription = "Exit"
  val CancelCommand = "c"
  val CancelDescription = "Cancel"
  val MenuHeading = "# PLAYER-MENU #"
  val PlayerCommand = "p"
  val PlayerDescription = "Add player"
  val PlayerAdded = "PlayerAdded"
  def apply(controller: Controller): MenuPlayer = new MenuPlayer(controller, MenuPlayerName(controller))
}
