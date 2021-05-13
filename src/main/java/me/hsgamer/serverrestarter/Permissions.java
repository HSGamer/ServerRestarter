package me.hsgamer.serverrestarter;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission RELOAD = new Permission("restarter.reload", PermissionDefault.OP);
    public static final Permission FORCE_RESTART = new Permission("restarter.forcerestart", PermissionDefault.OP);
    public static final Permission SKIP = new Permission("restarter.skip", PermissionDefault.OP);
    public static final Permission CHECK = new Permission("restarter.check", PermissionDefault.OP);

    private Permissions() {
        // EMPTY
    }
}
