package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import org.bukkit.Material;

/**
 *
 * @author Tre Logan
 */
public class TriFill implements Filler {

    private short colorOne;
    private short colorTwo;
    private short colorThree;

    public TriFill(FillColor colorOne, FillColor colorTwo, FillColor colorThree) {
        this.colorOne = colorOne.getShort();
        this.colorTwo = colorTwo.getShort();
        this.colorThree = colorThree.getShort();
    }

    @Override
    public void fill(Menu menu) {
        int counter = 0;
        for (int i = counter; i < menu.getSlots(); i++) {
            MenuItem menuItem = new MenuItem("")
                    .setMaterial(Material.STAINED_GLASS_PANE);

            switch (counter) {
                case 0:
                    menuItem.setDurability(colorOne);
                    break;
                case 1:
                    menuItem.setDurability(colorTwo);
                    break;
                case 2:
                    menuItem.setDurability(colorThree);
                    break;
                default:
                    counter = 0;
            }

            counter++;

            menu.addItem(i, menuItem);
        }
    }

}
