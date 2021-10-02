package io.yeah.lib.file

import org.bukkit.Bukkit
import java.io.File

val rootDir = File("plugins/yeah")
val playerDir = File(rootDir, "/players")

fun checkDir() {
  if (!rootDir.exists()) rootDir.mkdirs()
  if (!playerDir.exists()) rootDir.mkdirs()

  Bukkit.getConsoleSender().sendMessage("Folder created!")
}
