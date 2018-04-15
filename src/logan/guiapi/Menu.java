package logan.guiapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import logan.guiapi.fill.Filler;
import logan.guiapi.util.PlaceholderParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Tre Logan
 */
public class Menu implements Listener, Cloneable {

    private static boolean registered;

    private String title;
    private Inventory inventory;
    private int slots;

    private Map<Integer, MenuItem> menuItems = new HashMap<>();

    public Menu() {
        if (!registered) {
            Main.registerEvents(this);
        }
        registered = true;
    }

    public Menu(String title, int rows) {
        this();
        this.title = title;
        slots = rows * 9;
    }

    public void show(Player player) {
        parsePlaceholders(player);
        inventory = Bukkit.createInventory(null, slots, title);
        menuItems.forEach((s, mi) -> inventory.setItem(s, mi.getItemStack()));
        player.openInventory(inventory);
    }

    private void parsePlaceholders(Player player) {
        title = PlaceholderParser.parse(title, player);
        menuItems.forEach((s, mi) -> {
            mi.setName(PlaceholderParser.parse(mi.getName(), player));
            List<String> lore = mi.getLore().stream()
                    .map(l -> PlaceholderParser.parse(l, player))
                    .collect(Collectors.toList());
            mi.setLore(lore);
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(int rows) {
        this.slots = rows * 9;
    }

    public void fill(Filler fillPattern) {
        fillPattern.fill(this);
    }

    public void addItem(int slot, MenuItem menuItem) {
        menuItems.put(slot, menuItem);
    }

    public void removeItem(int slot, MenuItem menuItem) {
        menuItems.remove(slot);
    }

    public String getTitle() {
        return title;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSlots() {
        return slots;
    }

    public int getTopLeft() {
        return 0;
    }

    public int getTopRight() {
        return 8;
    }

    public int getBottomLeft() {
        return inventory.getSize() - 9;
    }

    public int getBottomRight() {
        return inventory.getSize() - 1;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        String invName = event.getInventory().getTitle();

        if (!invName.equals(title)) {
            return;
        }

        event.setCancelled(true);
        menuItems.keySet().stream()
                .filter(s -> s == event.getSlot())
                .forEach(s -> menuItems.get(s).onClick(new MenuItemClickEvent(event)));

    }

}
