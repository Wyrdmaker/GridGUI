import scala.swing._

object Demineur extends SimpleGUIApplication {
	def top = new MainFrame {
		title = "MainFrame"
		contents = new Button {
			text = "Click Me!"
		}
	}
}
