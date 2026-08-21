# Baritone AutoBuild Litematica Patch (1.21.11 Fabric)

## What this does

1. **`#build` with no args** (or key **K**) → builds the **currently selected** Litematica placement, or the first **enabled** one.
2. Origin = placement absolute world origin (rotation + mirror applied). **Never player position.**
3. **Litematica Render Layer** is respected via `DataManager.getRenderLayerRange()`.
4. **HUD** top-left: AutoBuild ON/OFF, State, Layer Y, Missing, Wrong, Ignored, Reset countdown.
5. **Soft reset every 60s**, **full rescan every 10 minutes**.
6. A* / Navigator / movement / parkour / place / break algorithms **unchanged**.

## How to apply

Copy files from this patch into the matching paths of the Baritone 1.21.11 source tree:

```
src/schematica_api/java/fi/dy/masa/litematica/data/DataManager.java
src/schematica_api/java/fi/dy/masa/litematica/schematic/placement/SchematicPlacement.java
src/schematica_api/java/fi/dy/masa/litematica/schematic/placement/SchematicPlacementManager.java
src/schematica_api/java/fi/dy/masa/malilib/util/LayerRange.java          ← NEW
src/api/java/baritone/api/process/IBuilderProcess.java
src/main/java/baritone/utils/schematic/litematica/LitematicaHelper.java
src/main/java/baritone/process/BuilderProcess.java
src/main/java/baritone/command/defaults/BuildCommand.java
src/main/java/baritone/utils/AutoBuildHud.java                           ← NEW
src/main/java/baritone/utils/AutoBuildKeybind.java                       ← NEW
src/main/java/baritone/launch/BaritoneFabricClient.java                  ← NEW
fabric/src/main/resources/fabric.mod.json
```

## Build requirements

- Litematica + malilib installed at runtime (same MC version).
- Fabric API (keybinding + lifecycle + rendering-v1) for HUD and keybind.
- `fabric.mod.json` already declares `"fabric-api": "*"` and client entrypoint.

If your Litematica build renames `getAllSchematicsPlacements` ↔ `getAllSchematicPlacements`, fix the stub name (Baritone already documents this).

## Usage

1. Load a schematic in Litematica and create a placement.
2. Move / rotate / mirror it as needed; select it (or leave one enabled).
3. Press **K** or type `#build`.
4. Press **K** again to stop.
5. Optional: `#litematica <index>` still works (also applies layer mask).

## Notes

- Stubs throw `LinkageError` only if called without Litematica on the classpath; with Litematica present, the real classes are used.
- Layer lock updates immediately because every `partOfMask` call queries the live `LayerRange`.
- Smart scan reuses Baritone’s existing `incorrectPositions` set and classifies Missing / Wrong / Ignored for the HUD.
)
