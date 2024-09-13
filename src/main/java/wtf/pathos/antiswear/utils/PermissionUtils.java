package wtf.pathos.antiswear.utils;

import org.bukkit.command.CommandSender;

public class PermissionUtils {
    public static boolean hasPermission(CommandSender sender, String permission) {
        return sender.hasPermission(permission);
    }
}