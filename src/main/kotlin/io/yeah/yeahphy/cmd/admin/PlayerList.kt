package io.yeah.yeahphy.cmd.admin

import io.yeah.lib.player.currentPlayer
import io.yeah.lib.player.getYeahPlayerByName
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class PlayerList : CommandExecutor {
  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
    if (!sender.isOp) {
      sender.sendMessage("${ChatColor.RED}Permission Denied")
      return true
    }

    return if (args.isEmpty()) {
      currentPlayer.forEach { p -> sender.sendMessage("${p.name} (exp: ${p.exp})") }
      true
    } else {
      val player = getYeahPlayerByName(args[0])
      if (player == null) {
        sender.sendMessage("Invalid player")
        false
      } else {
        sender.sendMessage("===== ${player.name} =====")
        sender.sendMessage("UUID : ${player.uuid}")
        sender.sendMessage("Exp : ${player.exp}")
        true
      }
    }
  }
}
