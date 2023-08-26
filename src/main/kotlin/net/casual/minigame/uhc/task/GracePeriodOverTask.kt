package net.casual.minigame.uhc.task

import net.casual.arcade.scheduler.SavableTask
import net.casual.minigame.uhc.UHCMinigame

class GracePeriodOverTask(
    private val uhc: UHCMinigame
): SavableTask {
    override val id = ID

    override fun run() {
        this.uhc.onGraceOver()
    }

    companion object {
        const val ID = "grace_period_over_task"
    }
}