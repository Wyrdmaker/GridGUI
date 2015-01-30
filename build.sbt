lazy val root = (project in file(".")).
	settings(
		name := "Projet_Partie_1",
		scalaVersion := "2.10.4",
		libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10+"
	)