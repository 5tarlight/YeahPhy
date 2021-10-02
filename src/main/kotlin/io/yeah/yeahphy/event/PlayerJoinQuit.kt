package io.yeah.yeahphy.event

import io.yeah.lib.player.getYeahPlayerByName
import io.yeah.lib.player.playerJoin
import io.yeah.lib.player.playerQuit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerJoinQuit : Listener {
  @EventHandler
  fun onPlayerJoin(event: PlayerJoinEvent) {
    event.joinMessage = "${ChatColor.YELLOW}+${event.player.name}"
    playerJoin(event.player)
    val player = getYeahPlayerByName(event.player.name)
    if (player != null) {
      event.player.sendMessage(player.toString())
    }
  }

  @EventHandler
  fun onPlayerQuit(event: PlayerQuitEvent) {
    event.quitMessage = "${ChatColor.RED}-${event.player.name}"
    playerQuit(getYeahPlayerByName(event.player.name))
  }
}
