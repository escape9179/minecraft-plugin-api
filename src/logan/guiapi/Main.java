package logan.guiapi;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tre Logan
 */
public class Main extends JavaPlugin {

    private static JavaPlugin plugin;
    
    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info(getName() + " enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled.");
    }
    
    public static void registerEvents(Listener listener) {
        plugin.getServer()
                .getPluginManager()
                .registerEvents(listener, plugin);
    }
    
    public static void unregisterEvents(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
    
    public static JavaPlugin getPlugin() {
        return plugin;
    }
    
}
