package logan.guiapi;

import logan.guiapi.util.PlaceholderManager;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tre Logan
 */
public class GUIAPI extends JavaPlugin {
    
    private static GUIAPI plugin;

    private static Map<Integer, Listener> menuListeners = new HashMap<>();
    private static PlaceholderManager placeholderManager = new PlaceholderManager();
    
    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info(getName() + " enabled.");
    }
    
    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled.");
    }

    public static void addMenuListener(int id, Listener listener) {
        if (menuListeners.containsKey(id)) return;
        menuListeners.put(id, listener);
        registerListener(listener);
    }
    
    public static void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public static PlaceholderManager getPlaceholderManager() {
        return placeholderManager;
    }
}
