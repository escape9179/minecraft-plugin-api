package logan.guiapi.fill;

import java.util.Arrays;

/**
 *
 * @author Tre Logan
 */
public enum FillColor {
    
    WHITE(0, "white"),
    ORANGE(1, "orange"),
    MAGENTA(2, "magenta"),
    LIGHT_BLUE(3, "lightblue"),
    YELLOW(4, "yellow"),
    LIME(5, "lime"),
    PINK(6, "pink"),
    GRAY(7, "gray"),
    LIGHT_GRAY(8, "lightgray"),
    CYAN(9, "cyan"),
    PURPLE(10, "purple"),
    BLUE(11, "blue"),
    BROWN(12, "brown"),
    GREEN(13, "green"),
    RED(14, "red"),
    BLACK(15, "black");
    
    private short colorShort;
    private String colorString;
    
    FillColor(int colorShort, String colorString) {
        this.colorShort = (short) colorShort;
        this.colorString = colorString;
    }
    
    public static FillColor getFillColor(short color) {
        return Arrays.stream(values())
                .filter(val -> val.getShort() == color)
                .findAny()
                .orElse(null);
    }
    
    public static FillColor getFillColor(String color) {
        return Arrays.stream(values())
                .filter(val -> val.getString().equals(color))
                .findAny()
                .orElse(null);
    }
    
    public short getShort() {
        return colorShort;
    }
    
    public String getString() {
        return colorString;
    }
    
}
