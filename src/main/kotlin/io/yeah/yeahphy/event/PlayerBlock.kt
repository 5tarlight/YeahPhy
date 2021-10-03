package io.yeah.yeahphy.event

import io.yeah.lib.player.currentPlayer
import io.yeah.lib.player.getPlayerIndexByName
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class PlayerBlock : Listener {
  @EventHandler
  fun onDestroyBlock(event: BlockBreakEvent) {
    // val block = event.block
    // val index = getPlayerIndexByName(event.player.name)

    if (getPlayerIndexByName(event.player.name) == -1) {
      event.isCancelled = true
      return
    }

    // currentPlayer[getPlayerIndexByName(event.player.name)].exp(1.0)
  }
}
