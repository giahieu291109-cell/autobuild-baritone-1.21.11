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

package fi.dy.masa.malilib.util;

import net.minecraft.core.BlockPos;

/**
 * Compile-time stub matching malilib LayerRange used by Litematica.
 * At runtime the real malilib class is loaded.
 */
public class LayerRange {

    public boolean isPositionWithinRange(BlockPos pos) {
        throw new LinkageError();
    }

    public boolean isPositionWithinRange(int x, int y, int z) {
        throw new LinkageError();
    }

    public int getLayerMin() {
        throw new LinkageError();
    }

    public int getLayerMax() {
        throw new LinkageError();
    }

    public int getCurrentLayerInteger() {
        throw new LinkageError();
    }
}
