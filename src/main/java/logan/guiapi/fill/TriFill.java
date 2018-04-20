package logan.guiapi.fill;

import logan.guiapi.Menu;

/**
 *
 * @author Tre Logan
 */
public class TriFill implements Filler {

    private FillColor colorOne;
    private FillColor colorTwo;
    private FillColor colorThree;

    public TriFill(FillColor colorOne, FillColor colorTwo, FillColor colorThree) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        this.colorThree = colorThree;
    }

    @Override
    public void fill(Menu menu) {

        FillPlacer fillPlacer = new FillPlacer(colorOne);
        fillPlacer.placeIntermittently(0, 3, menu);

        fillPlacer.setFill(colorTwo);
        fillPlacer.placeIntermittently(1, 3, menu);

        fillPlacer.setFill(colorThree);
        fillPlacer.placeIntermittently(2, 3, menu);

    }

}
