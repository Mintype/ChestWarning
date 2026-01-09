package org.mintype.chestwarningmod.storage;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;

/**
 * Class to store and persist data on if a player has been warned or not
 */
public class WarnedPlayersStorage {
    public static final AttachmentType<Boolean> WARNED = AttachmentRegistry.<Boolean>builder()
            .persistent(Codec.BOOL)      // Ensures it saves to the player's NBT (dat file)
            .copyOnDeath()               // Ensures the value stays true after the player respawns
            .initializer(() -> false)    // Default value for new players
            .buildAndRegister(Identifier.of("chestwarningmod", "warned"));

    public static void init() {
        // Calling this triggers the static field above
        // *necessary btw*
    }

    /**
     * Checks if the player has already been warned
     */
    public static boolean hasBeenWarned(ServerPlayerEntity player) {
        // If the data is null, it returns false
        return player.getAttachedOrElse(WARNED, false);
    }

    /**
     * Sets the warning status for the player
     */
    public static void setWarned(ServerPlayerEntity player, boolean warned) {
        player.setAttached(WARNED, warned);
    }
}