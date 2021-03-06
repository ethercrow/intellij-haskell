package intellij.haskell.runconfig.console

import java.util.concurrent.ConcurrentHashMap

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import intellij.haskell.HaskellFile

import scala.collection.JavaConverters._

object HaskellConsoleViewMap {
  private val consoleViews = new ConcurrentHashMap[Editor, HaskellConsoleView]().asScala

  def addConsole(console: HaskellConsoleView) {
    consoleViews.put(console.getConsoleEditor, console)
  }

  def delConsole(console: HaskellConsoleView) {
    consoleViews.remove(console.getConsoleEditor)
  }

  def getConsole(editor: Editor): Option[HaskellConsoleView] = {
    consoleViews.get(editor)
  }

  def getConsole(editor: Project): Option[HaskellConsoleView] = {
    consoleViews.values.find(console => console.project == editor && console.isShowing)
  }

  // File is project file and not file which represents console
  val projectFileByConfigName = new ConcurrentHashMap[String, HaskellFile]().asScala
}
