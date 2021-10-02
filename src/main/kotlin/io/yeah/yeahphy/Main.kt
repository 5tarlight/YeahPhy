package io.yeah.yeahphy

import io.yeah.lib.file.checkDir
import io.yeah.yeahphy.event.PlayerJoinQuit
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
  override fun onLoad() {
    Bukkit.getConsoleSender().sendMessage("Loading YeahPhy")
    checkDir()
  }

  override fun onEnable() {
    server.pluginManager.registerEvents(PlayerJoinQuit(), this)

    Bukkit.getConsoleSender().sendMessage("YeahPhy Enabled!")
  }

  override fun onDisable() {
    Bukkit.getConsoleSender().sendMessage("YeahPhy Disabled")
  }
}
