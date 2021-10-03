package io.yeah.lib.player

import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Sound
import org.bukkit.entity.Firework
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
      e -= level * 100
    }

    return level
  }

  fun toPlayerData(): PlayerData {
    return PlayerData(uuid, exp, money)
  }

  fun exp(get: Double) {
    val beforeLvl = calcLevel()
    val amplier = if (get >= 0) 1 else -1
    exp += get * amplier

    player.sendMessage("Exp gain : $get")

    if (beforeLvl != calcLevel()) {
      player.sendMessage("Level Up!")
      val fw = player.world.spawn(player.location, Firework::class.java)
      val fm = fw.fireworkMeta

      fm.addEffect(
        FireworkEffect.builder()
          .flicker(true)
          .trail(false)
          .with(FireworkEffect.Type.STAR)
          .with(FireworkEffect.Type.BALL)
          .with(FireworkEffect.Type.BALL_LARGE)
          .withColor(Color.AQUA)
          .withColor(Color.YELLOW)
          .withColor(Color.RED)
          .withColor(Color.RED)
          .withColor(Color.WHITE)
          .build()
      )

      fm.power = 0
      fw.fireworkMeta = fm

      player.playSound(player.location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.0f)
      player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f)
    }
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
