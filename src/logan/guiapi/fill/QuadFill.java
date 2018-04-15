package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import org.bukkit.Material;

/**
 *
 * @author Tre Logan
 */
public class QuadFill implements Filler {

    private short colorOne;
    private short colorTwo;
    private short colorThree;
    private short colorFour;

    public QuadFill(FillColor colorOne, FillColor colorTwo, FillColor colorThree, FillColor colorFour) {
        this.colorOne = colorOne.getShort();
        this.colorTwo = colorTwo.getShort();
        this.colorThree = colorThree.getShort();
        this.colorFour = colorFour.getShort();
    }

    @Override
    public void fill(Menu menu) {
        int counter = 0;
        for (int i = counter; i < menu.getSlots(); i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setMaterial(Material.STAINED_GLASS_PANE);
            menuItem.clearName();

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
                case 3:
                    menuItem.setDurability(colorFour);
                    break;
                default:
                    counter = 0;
            }

            counter++;
            
            menu.addItem(i, menuItem);
        }
    }

}
