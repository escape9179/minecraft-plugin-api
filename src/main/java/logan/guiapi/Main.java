package logan.guiapi;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tre Logan
 */
public class Main extends JavaPlugin {
    
    private static JavaPlugin plugin;
    
    private static Map<String, Listener> menuListeners = new HashMap<>();
    
    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info(getName() + " enabled.");
    }
    
    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled.");
    }
    
    public static void addMenuListener(String id, Listener listener) {
        Listener previous = menuListeners.put(id, listener);
        if (previous == null) {
            registerListener(listener);
        }
        
    }
    
    public static void removeMenuListener(String id) {
        menuListeners.remove(id);
    }
    
    public static JavaPlugin getPlugin() {
        return plugin;
    }
    
    private static void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
    
}
