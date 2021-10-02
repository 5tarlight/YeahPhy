package io.yeah.lib.player

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.Serializable
import java.util.*

class YeahPlayer(var player: Player) {
  val name: String
    get() { return player.name }

  val uuid: String
    get() { return player.uniqueId.toString() }

  var exp: Double = 0.0
  var money: Double = 5000.0

  constructor(data: PlayerData) : this(Bukkit.getPlayer(UUID.fromString(data.uuid))!!) {
    exp = data.exp
    money = data.money
  }

  fun calcLevel(): Int {
    var e = exp
    var level = 0

    while (e - level * 10 > 0) {
      level++
      e -= level * 10
    }

    return level + 1
  }

  fun toPlayerData(): PlayerData {
    return PlayerData(uuid, exp, money)
  }

  override fun toString(): String {
    return """
      ========== $name ==========
      UUID : $uuid
      Level : ${calcLevel()} ($exp)
      Money : $money
      ${"=".repeat("========== $name ==========".length)}
    """.trimIndent()
  }
}

data class PlayerData(val uuid: String, var exp: Double, var money: Double) : Serializable
