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

  constructor(data: PlayerData) : this(Bukkit.getPlayer(UUID.fromString(data.uuid))!!) {
    exp = data.exp
  }

  fun toPlayerData(): PlayerData {
    return PlayerData(uuid, exp)
  }
}

data class PlayerData(val uuid: String, var exp: Double) : Serializable
