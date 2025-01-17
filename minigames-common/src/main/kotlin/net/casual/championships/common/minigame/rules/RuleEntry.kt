package net.casual.championships.common.minigame.rules

import net.casual.arcade.utils.TimeUtils.Seconds
import net.casual.arcade.utils.time.MinecraftTimeDuration
import net.minecraft.network.chat.Component

class RuleEntry(
    val lines: List<Component>,
    val duration: MinecraftTimeDuration
) {
    class Builder {
        private val lines = ArrayList<Component>()

        var duration: MinecraftTimeDuration = 6.Seconds

        fun line(component: Component) {
            this.lines.add(component)
        }

        fun build(): RuleEntry {
            return RuleEntry(this.lines, this.duration)
        }
    }
}