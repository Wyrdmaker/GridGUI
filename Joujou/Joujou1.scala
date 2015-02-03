import scala.swing._
import scala.swing.event._

class CaseLabel (p : UI, n : Int, v : String) extends Label {
	private var discovered = false
        listenTo(mouse.moves, mouse.clicks)
        reactions += {
                case e : MouseEntered =>
			if (!discovered)
                        	border = Swing.LineBorder(new Color(255,0,0))
                case e : MouseExited =>
			if (!discovered)
                		border = Swing.LineBorder(new Color(0,0,0))
		case e : MouseClicked =>
			discoverMe()
        }
	def discoverMe() : Unit = {
		if (!discovered) {
			border = Swing.LineBorder(new Color(0,0,255))
                	discovered = true
			p.add()
			text = v
			v match {
				case "b" => p.lose()
				case " " => p.spread(n)
				case _   => ()
			}
		}
	}
}

class GrilleMode(x : Int = 0, y : Int = 0, b : Int = 0) extends MenuItem(""){
	action = new Action("Grille " + x + "x" + y + " (" + b + ")") {
		def apply {
			println(text)
		}
	}
	if (b == 0) {
		action = new Action("Restart") {
                	def apply {
                        	println(text)
                	}
       		}
	}
}

class UI extends MainFrame {
	var x = 5
	var y = 3
	var t = this
	var nbDiscovered = 0
	var nbBombs = 0
	var lstLabel : IndexedSeq[CaseLabel] = IndexedSeq()
	title = "GUI Program #1"
	preferredSize = new Dimension(340, 240)
	menuBar = new MenuBar {
                contents += new Menu("Game") {
                        contents += new GrilleMode(9,9,10)
			contents += new GrilleMode(16,16,40)
			contents += new GrilleMode(16,16,99)
			contents += new GrilleMode()
                }
		contents += new Menu("About") {
                        contents += new MenuItem(new Action("Prout") {
                                def apply {
                                        println("New Game")
                                }
                        })
		}
        }
	contents = new GridPanel(y,x){
		lstLabel = 0 until (x * y) map (n => new CaseLabel(t, n, " "){
			border = Swing.LineBorder(new Color(0,0,0))
		})
		contents ++= lstLabel
	}
	def add() = {
		nbDiscovered += 1
		if (nbDiscovered + nbBombs == x * y)
			win()
	}
	def neighbour(n : Int) : List[Int] = {
		var lst : List[Int]= List()
		var a = if (n % y == 0) 0 else -1
                var b = if (n % y == y - 1) 0 else 1
                var c = if (n < y) 0 else -1
                var d = if (n >= (x - 1) * y) 0 else 1
                for (i <- a to b) {
                        for (j <- c to d) {
                                if (0 <= n + j * x + i && n + j * x + i < x * y) {
                                        lst ++= List(n + j * x + i) // LOLILOOOL
                                }
                        }
                }
		return lst	
	}
	def spread(n : Int) = {
		var lst = neighbour(n)
		lst.foreach(n => lstLabel(n).discoverMe())
	}
	def lose() = {

	}
	def win() = {

	}
}

object GuiProgramOne {
	def main(args: Array[String]) {
		val ui = new UI
		ui.visible = true
	}
}
