package logan.guiapi.fill;

import logan.guiapi.Menu;
import org.bukkit.Material;

/**
 *
 * @author Tre Logan
 */
public class UniFill implements Filler {

    private Material fillMaterial;

    public UniFill(Material fillMaterial) {
        this.fillMaterial = fillMaterial;
    }

    @Override
    public void fill(Menu menu) {
        FillPlacer fillPlacer = new FillPlacer(fillMaterial);
        fillPlacer.placeIntermittently(0, 1, menu);
    }
}
