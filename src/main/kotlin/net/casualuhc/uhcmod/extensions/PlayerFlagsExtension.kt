package net.casualuhc.uhcmod.extensions

import net.casualuhc.arcade.events.EventHandler
import net.casualuhc.arcade.extensions.DataExtension
import net.casualuhc.arcade.utils.PlayerUtils.getExtension
import net.casualuhc.uhcmod.events.player.PlayerFlagEvent
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerPlayer
import java.util.*

class PlayerFlagsExtension(
    val player: ServerPlayer
): DataExtension {
    private val flags = EnumSet.noneOf(PlayerFlag::class.java)

    fun has(flag: PlayerFlag): Boolean {
        return this.flags.contains(flag)
    }

    fun set(flag: PlayerFlag, set: Boolean) {
        if (set) {
            if (!this.flags.add(flag)) {
                return
            }
        } else if (!this.flags.remove(flag)) {
            return
        }
        EventHandler.broadcast(PlayerFlagEvent(this.player, flag, set))
    }

    fun toggle(flag: PlayerFlag) {
        this.set(flag, !this.has(flag))
    }

    fun get(): Collection<PlayerFlag> {
        return this.flags
    }

    fun clear() {
        for (flag in PlayerFlag.values()) {
            this.set(flag, false)
        }
    }

    override fun getName(): String {
        return "UHC_PlayerFlags"
    }

    override fun deserialize(element: Tag) {
        (element as ListTag).forEach {
            this.flags.add(PlayerFlag.valueOf(it.asString))
        }
    }

    override fun serialize(): Tag {
        val tag = ListTag()
        for (flag in this.flags) {
            tag.add(StringTag.valueOf(flag.name))
        }
        return tag
    }

    companion object {
        val ServerPlayer.flags
            get() = this.getExtension(PlayerFlagsExtension::class.java)
    }
}