package io.yeah.lib.file

import io.yeah.lib.player.YeahPlayer

fun savePlayerFile(player: YeahPlayer) {
  val data = player.toPlayerData()
}