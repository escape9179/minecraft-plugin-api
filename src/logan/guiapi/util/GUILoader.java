package logan.guiapi.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import logan.guiapi.Menu;
import logan.guiapi.MenuItem;
import logan.guiapi.fill.BiFill;
import logan.guiapi.fill.FillColor;
import logan.guiapi.fill.QuadFill;
import logan.guiapi.fill.TriFill;
import logan.guiapi.fill.UniFill;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

/**
 *
 * @author Tre Logan
 */
public class GUILoader {

    private static final String MENU_ELEMENT = "menu";
    private static final String ITEM_ELEMENT = "item";
    private static final String ID_ATTRIBUTE = "id";

    private Class<?> clazz;
    private String path;

    public GUILoader(Class<?> clazz, String path) {
        this.clazz = clazz;
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MenuItem loadItemFromXML(String id) {
        XMLStreamReader reader = setupStream();
        MenuItem menuItem = null;

        try {
            while (reader.hasNext()) {
                int code = reader.next();

                if (code != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }

                String elementName = reader.getName().toString();
                int count = reader.getAttributeCount();

                if (elementName.equals(ITEM_ELEMENT)) {
                    String itemVar = getAttribute(reader, ID_ATTRIBUTE).getValue();
                    if (!itemVar.equals(id)) {
                        continue;
                    }

                    menuItem = parseXMLItemAttributes(reader, count);
                }
            }

            reader.close();

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return menuItem;
    }

    public Menu loadMenuFromXML(String id) {
        XMLStreamReader reader = setupStream();
        Menu menu = null;

        try {
            while (reader.hasNext()) {
                int code = reader.next();

                if (code != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }

                String elementName = reader.getName().toString();
                int count = reader.getAttributeCount();

                if (elementName.equals(MENU_ELEMENT)) {
                    String menuVar = getAttribute(reader, ID_ATTRIBUTE).getValue();
                    if (!menuVar.equals(id)) {
                        continue;
                    }

                    menu = parseXMLMenuAttributes(reader, count);
                }
            }

            reader.close();

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return menu;
    }

    private XMLStreamReader setupStream() {
        InputStream stream = clazz.getResourceAsStream(path);
        XMLStreamReader reader;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(stream);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return reader;
    }

    private Menu parseXMLMenuAttributes(XMLStreamReader reader, int count) {
        Menu menu = new Menu();

        for (int i = 0; i < count; i++) {

            String name = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);

            switch (name) {
                case "title":
                    menu.setTitle(value);
                    break;
                case "rows":
                    menu.setRows(Integer.parseInt(value));
                    break;
                case "unifill":
                    FillColor color = FillColor.getFillColor(value);
                    menu.fill(new UniFill(color));
                    break;
                case "bifill": {
                    String[] vals = value.split(",");
                    FillColor colorOne = FillColor.getFillColor(vals[0].trim());
                    FillColor colorTwo = FillColor.getFillColor(vals[1].trim());
                    menu.fill(new BiFill(colorOne, colorTwo));
                }
                break;
                case "trifill": {
                    String[] vals = value.split(",");
                    FillColor colorOne = FillColor.getFillColor(vals[0].trim());
                    FillColor colorTwo = FillColor.getFillColor(vals[1].trim());
                    FillColor colorThree = FillColor.getFillColor(vals[2].trim());
                    menu.fill(new TriFill(colorOne, colorTwo, colorThree));
                }
                break;
                case "quadfill": {
                    String[] vals = value.split(",");
                    FillColor colorOne = FillColor.getFillColor(vals[0].trim());
                    FillColor colorTwo = FillColor.getFillColor(vals[1].trim());
                    FillColor colorThree = FillColor.getFillColor(vals[2].trim());
                    FillColor colorFour = FillColor.getFillColor(vals[3].trim());
                    menu.fill(new QuadFill(colorOne, colorTwo, colorThree, colorFour));
                }
                break;
            }
        }

        return menu;
    }

    private MenuItem parseXMLItemAttributes(XMLStreamReader reader, int count) {
        MenuItem menuItem = new MenuItem("");

        for (int i = 0; i < count; i++) {

            String name = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);

            switch (name) {
                case "material":
                    menuItem.setMaterial(Material.getMaterial(value.toUpperCase()));
                    break;
                case "clearName":
                    menuItem.clearName();
                    break;
                case "name":
                    menuItem.setName(ChatColor.translateAlternateColorCodes('^', value));
                    break;
                case "amount":
                    menuItem.setAmount(Integer.parseInt(value));
                    break;
                case "durability":
                    menuItem.setDurability(Short.parseShort(value));
                    break;
                case "magic":
                    menuItem.setMagic(Boolean.parseBoolean(value));
                    break;
                case "flags": {
                    String[] parts = value.split(",");
                    Stream<ItemFlag> flagStream = Arrays.stream(parts).map(ItemFlag::valueOf);
                    Set<ItemFlag> flagList = flagStream.collect(Collectors.toSet());
                    menuItem.addItemFlags(flagList);
                }
                break;
                case "lore": {
                    String[] parts = value.split(",");
                    Stream<String> partStream = Arrays.stream(parts).map(String::trim);
                    List<String> partList = partStream.collect(Collectors.toList());
                    menuItem.setLore(partList);
                }
                break;
                default:
            }
        }

        return menuItem;
    }

    private Attribute getAttribute(XMLStreamReader reader, String name) {
        int count = reader.getAttributeCount();
        XMLEventFactory eventFactory = XMLEventFactory.newFactory();

        for (int i = 0; i < count; i++) {
            String attName = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);
            if (attName.equals(name)) {
                return eventFactory.createAttribute(name, value);
            }
        }

        return null;
    }

}
