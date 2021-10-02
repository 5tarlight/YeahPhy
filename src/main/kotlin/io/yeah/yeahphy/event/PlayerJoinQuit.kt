package io.yeah.yeahphy.event

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerJoinQuit : Listener {
  @EventHandler
  fun onPlayerJoin(event: PlayerJoinEvent) {
    event.joinMessage = "${ChatColor.YELLOW}+${event.player.name}"
  }

  @EventHandler
  fun onPlayerQuit(event: PlayerQuitEvent) {
    event.quitMessage = "${ChatColor.RED}-${event.player.name}"
  }
}
