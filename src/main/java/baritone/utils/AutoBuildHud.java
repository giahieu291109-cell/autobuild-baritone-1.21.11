/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.utils;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.process.BuilderProcess;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

/**
 * Top-left AutoBuild HUD. Updated every client tick via HudRenderCallback.
 */
public final class AutoBuildHud {

    private AutoBuildHud() {}

    public static void register() {
        HudRenderCallback.EVENT.register(AutoBuildHud::render);
    }

    private static void render(GuiGraphics g, float tickDelta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) {
            return;
        }

        IBaritone baritone = BaritoneAPI.getProvider().getPrimaryBaritone();
        if (!(baritone.getBuilderProcess() instanceof BuilderProcess bp)) {
            return;
        }

        int x = 4;
        int y = 4;
        final int line = 10;

        boolean on = bp.isAutoBuildOn();
        draw(g, x, y, "AutoBuild: ", 0xAAAAAA);
        draw(g, x + 70, y, on ? "ON" : "OFF", on ? 0x55FF55 : 0xFF5555);
        y += line;

        draw(g, x, y, "State: ", 0xAAAAAA);
        draw(g, x + 42, y, bp.getAutoBuildState(), 0xFFFF55);
        y += line;

        draw(g, x, y, "Layer: Y=", 0xAAAAAA);
        draw(g, x + 55, y, String.valueOf(bp.getHudLayerY()), 0xFFFFFF);
        y += line;

        draw(g, x, y, "Missing: ", 0xAAAAAA);
        draw(g, x + 55, y, String.valueOf(bp.getMissingCount()), 0xFFFFFF);
        draw(g, x + 90, y, " | ", 0x888888);
        draw(g, x + 105, y, "Wrong: ", 0xAAAAAA);
        draw(g, x + 150, y, String.valueOf(bp.getWrongCount()), 0xFFFFFF);
        y += line;

        draw(g, x, y, "Ignored: ", 0xAAAAAA);
        draw(g, x + 55, y, String.valueOf(bp.getIgnoredCount()), 0xFFFFFF);
        draw(g, x + 90, y, " | ", 0x888888);
        draw(g, x + 105, y, "Reset: ", 0xAAAAAA);
        draw(g, x + 150, y, bp.getResetSecondsLeft() + "s", 0xFFFFFF);
    }

    private static void draw(GuiGraphics g, int x, int y, String s, int color) {
        g.drawString(Minecraft.getInstance().font, s, x, y, color, true);
    }
}
