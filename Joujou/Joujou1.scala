import scala.swing._

/*object Demineur extends SimpleGUIApplication {
	def top = new MainFrame {
		title = "MainFrame"
		contents = new Button {
			text = "Click Me!"
		}
	}
}*/
/*
Choisir une fonction présente dans 
override def mafonction() = super[Parent].mafonction()
*/
class UI extends MainFrame {
	title = "GUI Program #1"
	preferredSize = new Dimension(340, 240)
	contents = new GridPanel(3,5){
		name = "Quadrillage"

		for (x <- 1 to 15) {
		contents +=(new Label(){border = Swing.LineBorder(new Color(0,0,0))});
		}
	}
	menuBar = new MenuBar {
		contents += new Menu("Game") {
			contents += new MenuItem(new Action("New game") {
					def apply {
						println("New Game")
					}
					})
			contents += new MenuItem(new Action("Restart") {
					def apply {
						println("Restart")
					}
				})
			}
	}
}

object GuiProgramOne {
def main(args: Array[String]) {
val ui = new UI
ui.visible = true
}
}

