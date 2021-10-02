package io.yeah.yeahphy.event

import io.yeah.lib.file.loadPlayerFile
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerJoinQuit : Listener {
  @EventHandler
  fun onPlayerJoin(event: PlayerJoinEvent) {
    event.joinMessage = "${ChatColor.YELLOW}+${event.player.name}"
    val player = loadPlayerFile(event.player)
    event.player.sendMessage("Your current exp: ${player.exp}")
  }

  @EventHandler
  fun onPlayerQuit(event: PlayerQuitEvent) {
    event.quitMessage = "${ChatColor.RED}-${event.player.name}"
  }
}
