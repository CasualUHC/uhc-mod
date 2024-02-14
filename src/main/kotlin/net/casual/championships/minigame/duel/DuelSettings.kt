package net.casual.championships.minigame.duel

import net.casual.arcade.gui.screen.SelectionScreenStyle
import net.casual.arcade.settings.display.DisplayableGameSettingBuilder
import net.casual.arcade.settings.display.DisplayableGameSettingBuilder.Companion.bool
import net.casual.arcade.settings.display.DisplayableGameSettingBuilder.Companion.float64
import net.casual.arcade.settings.display.DisplayableSettings
import net.casual.arcade.utils.ComponentUtils.literal
import net.casual.arcade.utils.ItemUtils.hideTooltips
import net.casual.arcade.utils.ItemUtils.named
import net.casual.arcade.utils.ItemUtils.potion
import net.casual.arcade.utils.ScreenUtils
import net.casual.championships.items.MenuItem
import net.casual.championships.util.CasualScreenUtils
import net.casual.championships.util.CasualUtils
import net.minecraft.world.MenuProvider
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions

class DuelSettings: DisplayableSettings() {
    var teams by this.register(bool {
        name = "teams"
        display = Items.GREEN_BANNER.named("Teams")
        value = false
        defaultOptionsFor(this)
    })

    var health by this.register(float64 {
        name = "health"
        display = Items.POTION.named("Health").potion(Potions.HEALING).hideTooltips()
        value = 1.0
        option("normal", Items.RED_STAINED_GLASS_PANE.named("Normal"), 0.0)
        option("double", Items.YELLOW_STAINED_GLASS_PANE.named("Double"), 1.0)
        option("triple", Items.GREEN_STAINED_GLASS_PANE.named("Triple"), 2.0)
    })

    var naturalRegen by this.register(bool {
        name = "natural_regeneration"
        display = Items.GOLDEN_APPLE.named("Natural Regeneration")
        value = false
        defaultOptionsFor(this)
    })

    val borderRadius by this.register(float64 {
        name = "border_radius"
        display = Items.BARRIER.named("Border Radius")
        value = 10.0
        option("ten", Items.GREEN_STAINED_GLASS_PANE.named("Ten"), 10.0)
        option("twenty", Items.YELLOW_STAINED_GLASS_PANE.named("Twenty"), 20.0)
        option("thirty", Items.RED_STAINED_GLASS_PANE.named("Thirty"), 30.0)
    })

    val glowing by this.register(bool {
        name = "glowing"
        display = Items.SPECTRAL_ARROW.named("Glowing")
        value = false
        defaultOptionsFor(this)
    })

    var playerDropsHead by this.register(bool {
        name = "player_drops_head"
        display = Items.PLAYER_HEAD.named("Player Drops Head")
        value = true
        defaultOptionsFor(this)
    })

    override fun menu(parent: MenuProvider?): MenuProvider {
        return ScreenUtils.createSettingsMenu(
            this,
            components = CasualScreenUtils.named("Duel Settings".literal()),
            parent = parent,
            style = SelectionScreenStyle.centered(5, 3)
        )
    }

    override fun defaultOptionsFor(
        builder: DisplayableGameSettingBuilder<Boolean>,
        enabled: ItemStack,
        disabled: ItemStack
    ) {
        super.defaultOptionsFor(builder, MenuItem.TICK.named("Enabled"), MenuItem.CROSS.named("Disabled"))
    }
}