package io.yeah.yeahphy.cmd.admin

import io.yeah.lib.player.currentPlayer
import io.yeah.lib.player.getPlayerIndexByName
import io.yeah.lib.player.getYeahPlayerByName
import io.yeah.util.getPlayerNames
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class PlayerStat : CommandExecutor {
  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
    if (!sender.isOp) {
      sender.sendMessage("${ChatColor.RED}Permission Denied")
      return true
    }

    if (args.isEmpty() || args.size == 1)
      return false
    else if (args.size == 2) {
      val stat = args[1]
      val player = getYeahPlayerByName(args[0])

      if (player == null) {
        sender.sendMessage("${ChatColor.RED}No player found")
        return true
      }

      val value = when (stat) {
        "exp" -> player.exp
        "money" -> player.money
        else -> -1
      }

      sender.sendMessage("${player.name}'s $stat: $value")
      return true
    } else if (args.size == 3) {
      val stat = args[1]
      val value = args[2].toDouble()
      val index = getPlayerIndexByName(args[0])

      when (stat) {
        "exp" -> currentPlayer[index].exp = value
        "money" -> currentPlayer[index].money = value
        else -> {
          sender.sendMessage("${ChatColor.RED}Invalid Stat Property: $stat")
          return true
        }
      }

      sender.sendMessage("Set ${args[0]}'s $stat to $value")

      return true
    }
    return false
  }
}

class PlayerStatTabCompleter : TabCompleter {
  override fun onTabComplete(
    sender: CommandSender,
    command: Command,
    alias: String,
    args: Array<out String>
  ): MutableList<String> {
    return when (args.size) {
      1 -> getPlayerNames()
      2 -> mutableListOf("money", "exp")
      else -> mutableListOf()
    }
  }
}
