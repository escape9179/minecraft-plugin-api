package logan.guiapi.fill;

import logan.guiapi.Menu;

/**
 *
 * @author Tre Logan
 */
public class BiFill implements Filler {

    private FillColor colorOne;
    private FillColor colorTwo;

    public BiFill(FillColor colorOne, FillColor colorTwo) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
    }

    @Override
    public void fill(Menu menu) {

        FillPlacer fillPlacer = new FillPlacer(colorOne);
        fillPlacer.placeIntermittently(0, 2, menu);

        fillPlacer.setFill(colorTwo);
        fillPlacer.placeIntermittently(1, 2, menu);

    }

}
