package net.gah.hacks.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.gah.hacks.gahHacksMod;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TELEPORT = "key.category.gahhacksmod.teleport";
    public static final String KEY_TELEPORT_X_5 = "key.gahhacksmod.teleport_x_5";
    public static final String KEY_TELEPORT_Y_5 = "key.gahhacksmod.teleport_y_5";
    public static final String KEY_TELEPORT_Z_5 = "key.gahhacksmod.teleport_z_5";
    public static final String KEY_TELEPORT_REVERSE = "key.gahhacksmod.teleport_reverse";
    public static final String KEY_TELEPORT_TO_CROSSHAIR = "key.gahhacksmod.teleport_to_crosshair";


    public static KeyBinding teleportX5Key;
    public static KeyBinding teleportY5Key;
    public static KeyBinding teleportZ5Key;
    public static KeyBinding teleportReverseKey;
    public static KeyBinding teleportToCrosshairKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            double distance = 3;
            if (teleportToCrosshairKey.wasPressed()) {
                gahHacksMod.LOGGER.info("Teleport to crosshair pressed");

                if (teleportReverseKey.isPressed()) {
                    distance = 10;
                }

                double pitch = client.player.prevPitch * 0.017453292;
                double yaw = client.player.prevYaw * 0.017453292;

                double _x = -Math.cos(pitch) * Math.sin(yaw);
                double _y = -Math.sin(pitch);
                double _z = Math.cos(pitch) * Math.cos(yaw);

                double x = client.player.prevX + distance * _x;
                double y = client.player.prevY + distance * _y;
                double z = client.player.prevZ + distance * _z;

                gahHacksMod.LOGGER.info(x + " | " + y + " | " + z);
                client.player.setPosition(x, y, z);
            }

            if (teleportX5Key.wasPressed()) {
                gahHacksMod.LOGGER.info("teleportXKey pressed");
                if (teleportReverseKey.isPressed()) {
                    client.player.setPosition(client.player.prevX - 5, client.player.prevY, client.player.prevZ);
                    return;
                }
                client.player.setPosition(client.player.prevX + 5, client.player.prevY, client.player.prevZ);
            }

            if (teleportY5Key.wasPressed()) {
                gahHacksMod.LOGGER.info("teleportYKey pressed");
                if (teleportReverseKey.isPressed()) {
                    client.player.setPosition(client.player.prevX, client.player.prevY - 5, client.player.prevZ);
                    return;
                }
                client.player.setPosition(client.player.prevX, client.player.prevY + 5, client.player.prevZ);
            }

            if (teleportZ5Key.wasPressed()) {
                gahHacksMod.LOGGER.info("teleportZKey pressed");
                if (teleportReverseKey.isPressed()) {
                    client.player.setPosition(client.player.prevX, client.player.prevY, client.player.prevZ - 5);
                    return;
                }
                client.player.setPosition(client.player.prevX, client.player.prevY, client.player.prevZ + 5);
            }

            if (teleportReverseKey.wasPressed()) {
                gahHacksMod.LOGGER.info("teleportReverseKey pressed");
            }
        });
    }

    public static void register() {
        teleportX5Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TELEPORT_X_5,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                KEY_CATEGORY_TELEPORT
        ));

        teleportY5Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TELEPORT_Y_5,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                KEY_CATEGORY_TELEPORT
        ));

        teleportZ5Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TELEPORT_Z_5,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_TELEPORT
        ));

        teleportReverseKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TELEPORT_REVERSE,
                InputUtil.Type.MOUSE,
                GLFW.GLFW_MOUSE_BUTTON_5,
                KEY_CATEGORY_TELEPORT
        ));

        teleportToCrosshairKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TELEPORT_TO_CROSSHAIR,
                InputUtil.Type.MOUSE,
                GLFW.GLFW_MOUSE_BUTTON_4,
                KEY_CATEGORY_TELEPORT
        ));

        registerKeyInputs();
    }
}
