package io.yeah.yeahphy

import io.yeah.lib.file.checkDir
import io.yeah.lib.player.loadOnlinePlayers
import io.yeah.lib.player.saveAllPlayers
import io.yeah.yeahphy.cmd.admin.PlayerList
import io.yeah.yeahphy.cmd.admin.PlayerListTabCompleter
import io.yeah.yeahphy.cmd.admin.PlayerStat
import io.yeah.yeahphy.cmd.admin.PlayerStatTabCompleter
import io.yeah.yeahphy.event.PlayerJoinQuit
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
  override fun onLoad() {
    Bukkit.getConsoleSender().sendMessage("Loading YeahPhy")
  }

  override fun onEnable() {
    checkDir()
    loadOnlinePlayers()

    server.pluginManager.registerEvents(PlayerJoinQuit(), this)

    getCommand("player")?.setExecutor(PlayerList())
    getCommand("player")?.tabCompleter = PlayerListTabCompleter()
    getCommand("stat")?.setExecutor(PlayerStat())
    getCommand("stat")?.tabCompleter = PlayerStatTabCompleter()
    Bukkit.getConsoleSender().sendMessage("YeahPhy Enabled!")
  }

  override fun onDisable() {
    saveAllPlayers()
    Bukkit.getConsoleSender().sendMessage("YeahPhy Disabled")
  }
}
