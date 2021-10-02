package io.yeah.lib.file

import io.yeah.lib.player.PlayerData
import io.yeah.lib.player.YeahPlayer
import org.bukkit.entity.Player
import java.io.*

fun savePlayerFile(player: YeahPlayer) {
  val data = player.toPlayerData()
  val fos = FileOutputStream(File(playerDir, "/player-${player.uuid}.dat"))
  val oos = ObjectOutputStream(fos)

  oos.writeObject(data)

  oos.close()
  fos.close()
}

fun checkPlayerFileExists(uuid: String): Boolean {
  return File(playerDir, "/player-$uuid.dat").exists()
}

fun loadPlayerFile(player: Player): YeahPlayer {
  if (!checkPlayerFileExists(player.uniqueId.toString())) savePlayerFile(YeahPlayer(player))

  val fis = FileInputStream(File(playerDir, "/player-${player.uniqueId}.dat"))
  val ois = ObjectInputStream(fis)

  val data = ois.readObject() as PlayerData
  return YeahPlayer(data)
}
