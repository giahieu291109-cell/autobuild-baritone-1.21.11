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
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

/**
 * Configurable keybind (default K) to toggle AutoBuild on the active Litematica placement.
 */
public final class AutoBuildKeybind {

    private static KeyMapping toggleKey;

    private AutoBuildKeybind() {}

    public static void register() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.baritone.autobuild",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "key.categories.baritone"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                IBaritone b = BaritoneAPI.getProvider().getPrimaryBaritone();
                if (b.getBuilderProcess() instanceof BuilderProcess bp) {
                    bp.toggleAutoBuild();
                }
            }
        });
    }
}
