package logan.guiapi.fill;

import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FillPlacer {

    private ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE);

    public FillPlacer(FillColor fillColor) {
        itemStack.setDurability(fillColor.getShort());
    }

    public void setFill(FillColor fillColor) {
        itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, fillColor.getShort());
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
