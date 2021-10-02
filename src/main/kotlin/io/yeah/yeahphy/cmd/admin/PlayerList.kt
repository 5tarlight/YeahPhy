package io.yeah.yeahphy.cmd.admin

import io.yeah.lib.player.currentPlayer
import io.yeah.lib.player.getYeahPlayerByName
import io.yeah.util.getPlayerNames
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class PlayerList : CommandExecutor {
  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
    if (!sender.isOp) {
      sender.sendMessage("${ChatColor.RED}Permission Denied")
      return true
    }

    return if (args.isEmpty()) {
      if (currentPlayer.isEmpty()) {
        sender.sendMessage("No players")
        return true
      }

      currentPlayer.forEach { p -> sender.sendMessage("${p.name} (exp: ${p.exp})") }
      true
    } else {
      val player = getYeahPlayerByName(args[0])
      if (player == null) {
        sender.sendMessage("Invalid player")
        false
      } else {
        sender.sendMessage(player.toString())
        true
      }
    }
  }
}

class PlayerListTabCompleter : TabCompleter {
  override fun onTabComplete(
    sender: CommandSender,
    command: Command,
    alias: String,
    args: Array<out String>
  ): MutableList<String> {
    return when (args.size) {
      1 -> getPlayerNames()
      else -> mutableListOf()
    }
  }
}
