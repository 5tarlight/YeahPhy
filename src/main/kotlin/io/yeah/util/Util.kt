package io.yeah.util

import org.bukkit.Bukkit

fun getPlayerNames(): MutableList<String> {
  return Bukkit.getOnlinePlayers().map { p -> p.name }.toMutableList()
}
