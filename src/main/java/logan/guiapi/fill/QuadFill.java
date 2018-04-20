package logan.guiapi.fill;

import logan.guiapi.Menu;

/**
 *
 * @author Tre Logan
 */
public class QuadFill implements Filler {

    private FillColor colorOne;
    private FillColor colorTwo;
    private FillColor colorThree;
    private FillColor colorFour;

    public QuadFill(FillColor colorOne, FillColor colorTwo, FillColor colorThree, FillColor colorFour) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        this.colorThree = colorThree;
        this.colorFour = colorFour;
    }

    @Override
    public void fill(Menu menu) {

        FillPlacer fillPlacer = new FillPlacer(colorOne);
        fillPlacer.placeIntermittently(0, 4, menu);

        fillPlacer.setFill(colorTwo);
        fillPlacer.placeIntermittently(1, 4, menu);

        fillPlacer.setFill(colorThree);
        fillPlacer.placeIntermittently(2, 4, menu);

        fillPlacer.setFill(colorFour);
        fillPlacer.placeIntermittently(3, 4, menu);

    }

}