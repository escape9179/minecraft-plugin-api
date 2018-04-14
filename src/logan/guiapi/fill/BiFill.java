package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import logan.guiapi.MenuItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author Tre Logan
 */
public class BiFill implements Filler {

    private short colorOne;
    private short colorTwo;

    public BiFill(FillColor colorOne, FillColor colorTwo) {
        this.colorOne = colorOne.getShort();
        this.colorTwo = colorTwo.getShort();
    }

    @Override
    public void fill(Menu menu) {
        Inventory inventory = menu.getInventory();

        for (int i = 0; i < inventory.getSize(); i++) {
            MenuItemBuilder builder = new MenuItemBuilder();
            builder.setMaterial(Material.STAINED_GLASS_PANE);
            builder.clearName();
            builder.setDurability((i % 2) == 0 ? colorOne : colorTwo);
            
            MenuItem menuItem = builder.build();
            menu.addItem(i, menuItem);
        }
    }

}
