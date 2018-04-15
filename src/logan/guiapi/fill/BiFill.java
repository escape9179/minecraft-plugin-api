package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import org.bukkit.Material;

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
        for (int i = 0; i < menu.getSlots(); i++) {
            MenuItem menuItem = new MenuItem()
                    .setMaterial(Material.STAINED_GLASS_PANE)
                    .clearName()
                    .setDurability((i % 2) == 0 ? colorOne : colorTwo);

            menu.addItem(i, menuItem);
        }
    }

}
