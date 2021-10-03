package io.yeah.yeahphy.event

import io.yeah.lib.player.currentPlayer
import io.yeah.lib.player.getPlayerIndexByName
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerExpChangeEvent

class PlayerExp : Listener {
  @EventHandler
  fun onExpChange(event: PlayerExpChangeEvent) {
    currentPlayer[getPlayerIndexByName(event.player.name)].exp(event.amount.toDouble())
  }
}
