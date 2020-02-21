package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FillPlacer {

    private ItemStack itemStack;

    public FillPlacer(ItemStack fillItem) {
        setFill(fillItem);
    }

    public FillPlacer(Material material) {
        setFill(material);
    }

    public void setFill(ItemStack fillItem) {
        itemStack = fillItem;
    }

    public void setFill(Material material) {
        itemStack = new ItemStack(material);
    }

    public void placeIntermittently(int start, int spacing, Menu menu) {
        int size = menu.getSlots();
        double times = ((double) (size - start) / spacing);
        times = Math.ceil(times);

        int position = start;
        for(int i = 0; i < times; i++) {
            menu.addItem(position, new MenuItem("", itemStack));
            position += spacing;
        }
    }

}
