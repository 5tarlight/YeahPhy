package io.yeah.lib.player

import io.yeah.lib.file.loadPlayerFile
import io.yeah.lib.file.savePlayerFile
import org.bukkit.Bukkit
import org.bukkit.entity.Player

val currentPlayer = mutableListOf<YeahPlayer>()

fun playerJoin(player: Player) {
  val has = currentPlayer.any { player.uniqueId.toString() == it.uuid }
  if (!has) currentPlayer.add(loadPlayerFile(player))
}

fun getYeahPlayerByName(name: String): YeahPlayer? {
  val has = currentPlayer.any { name == it.name }
  if (!has) return null

  return currentPlayer.filter { name == it.name }[0]
}

fun playerQuit(player: YeahPlayer?) {
  val yeahPlayer = player?.let { getYeahPlayerByName(it.name) }
  yeahPlayer?.let { savePlayerFile(it) }
  currentPlayer.remove(yeahPlayer)
}

fun loadOnlinePlayers() {
  Bukkit.getConsoleSender().sendMessage("Reloading online player list")
  Bukkit.getOnlinePlayers().forEach { p -> playerJoin(p) }
}
