package logan.guiapi.fill;

import logan.guiapi.Menu;
import org.bukkit.Material;

/**
 *
 * @author Tre Logan
 */
public class BiFill implements Filler {

    private Material fillMaterial1;
    private Material fillMaterial2;

    public BiFill(Material fillMaterial1, Material fillMaterial2) {
        this.fillMaterial1 = fillMaterial1;
        this.fillMaterial2 = fillMaterial2;
    }

    @Override
    public void fill(Menu menu) {

        FillPlacer fillPlacer = new FillPlacer(fillMaterial1);
        fillPlacer.placeIntermittently(0, 2, menu);

        fillPlacer.setFill(fillMaterial2);
        fillPlacer.placeIntermittently(1, 2, menu);
    }
}
