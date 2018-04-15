package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import org.bukkit.Material;

/**
 *
 * @author Tre Logan
 */
public class UniFill implements Filler {

    private short color;

    public UniFill(FillColor color) {
        this.color = color.getShort();
    }

    @Override
    public void fill(Menu menu) {
        for (int i = 0; i < menu.getSlots(); i++) {
            MenuItem menuItem = new MenuItem("")
                    .setMaterial(Material.STAINED_GLASS_PANE)
                    .setDurability(color);

            menu.addItem(i, menuItem);

        }
    }

}
