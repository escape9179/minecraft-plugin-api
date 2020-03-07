package logan.guiapi;

import logan.guiapi.util.PlaceholderManager;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Tre Logan
 */
public class GUIAPI {

    private static Map<Integer, Menu> registeredMenus = new HashMap<>();
    private static PlaceholderManager placeholderManager = new PlaceholderManager();

    public static void registerMenu(int id, Menu menu) {
        if (registeredMenus.containsKey(id)) return;
        registeredMenus.put(id, menu);
    }

    public static PlaceholderManager getPlaceholderManager() {
        return placeholderManager;
    }

    public static void callInventoryClickEvents(InventoryClickEvent event) {
        for(Integer key : registeredMenus.keySet()) {
            registeredMenus.get(key).onInventoryClick(event);
        }
    }

    public static void callInventoryCloseEvents(InventoryCloseEvent event) {
        Iterator<Integer> menuIterator = registeredMenus.keySet().iterator();
        while(menuIterator.hasNext()) {
            Menu menu = registeredMenus.get(menuIterator.next());
            if (event.getPlayer().getUniqueId().equals(menu.getViewer().getUniqueId())) {
                registeredMenus.remove(menu.getId());
                menu.close();
            }
        }
    }
}
