package logan.guiapi.xml;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
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
import logan.guiapi.MenuItemBuilder;
import logan.guiapi.fill.BiFill;
import logan.guiapi.fill.QuadFill;
import logan.guiapi.fill.TriFill;
import logan.guiapi.fill.UniFill;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tre Logan
 */
public class GUILoader {

    private static final String MENU_ELEMENT = "menu";
    private static final String ITEM_ELEMENT = "item";
    private static final String ID_ATTRIBUTE = "id";

    public static MenuItem loadItemFromXML(JavaPlugin plugin, String id, String path) {
        XMLStreamReader reader = setupStream(plugin, path);
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

    public static Menu loadMenuFromXML(JavaPlugin plugin, String id, String path) {
        XMLStreamReader reader = setupStream(plugin, path);
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

                    menu = parseXMLMenuAttributes(plugin, reader, count);
                }
            }

            reader.close();

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
            return null;
        }

        return menu;
    }

    private static XMLStreamReader setupStream(JavaPlugin plugin, String path) {
        InputStream stream = plugin.getClass().getResourceAsStream(path);

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

    private static Menu parseXMLMenuAttributes(JavaPlugin plugin, XMLStreamReader reader, int count) {
        Menu menu = new Menu(plugin);

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
                    short shortVal = Short.parseShort(value);
                    menu.fill(new UniFill(shortVal));
                    break;
                case "bifill": {
                    String[] vals = value.split(",");
                    short valOne = Short.parseShort(vals[0].trim());
                    short valTwo = Short.parseShort(vals[1].trim());
                    menu.fill(new BiFill(valOne, valTwo));
                }
                break;
                case "trifill": {
                    String[] vals = value.split(",");
                    short valOne = Short.parseShort(vals[0].trim());
                    short valTwo = Short.parseShort(vals[1].trim());
                    short valThree = Short.parseShort(vals[2].trim());
                    menu.fill(new TriFill(valOne, valTwo, valThree));
                }
                break;
                case "quadfill": {
                    String[] vals = value.split(",");
                    short valOne = Short.parseShort(vals[0].trim());
                    short valTwo = Short.parseShort(vals[1].trim());
                    short valThree = Short.parseShort(vals[2].trim());
                    short valFour = Short.parseShort(vals[3].trim());
                    menu.fill(new QuadFill(valOne, valTwo, valThree, valFour));
                }
                break;
            }
        }

        return menu;
    }

    private static MenuItem parseXMLItemAttributes(XMLStreamReader reader, int count) {
        MenuItemBuilder builder = new MenuItemBuilder();

        for (int i = 0; i < count; i++) {

            String name = reader.getAttributeName(i).toString();
            String value = reader.getAttributeValue(i);

            switch (name) {
                case "material":
                    builder.setMaterial(Material.getMaterial(value.toUpperCase()));
                    break;
                case "clearName":
                    builder.clearName();
                    break;
                case "name":
                    builder.setName(ChatColor.translateAlternateColorCodes('&', value));
                    break;
                case "amount":
                    builder.setAmount(Integer.parseInt(value));
                    break;
                case "durability":
                    builder.setDurability(Short.parseShort(value));
                    break;
                case "magic":
                    builder.setMagic(Boolean.parseBoolean(value));
                    break;
                case "flags": {
                    String[] parts = value.split(",");
                    Stream<ItemFlag> flagStream = Arrays.stream(parts).map(ItemFlag::valueOf);
                    List<ItemFlag> flagList = flagStream.collect(Collectors.toList());
                    builder.addItemFlags(flagList);
                }
                break;
                case "lore": {
                    String[] parts = value.split(",");
                    Stream<String> partStream = Arrays.stream(parts).map(String::trim);
                    List<String> partList = partStream.collect(Collectors.toList());
                    builder.setLore(partList);
                }
                break;
                default:
            }
        }

        return builder.build();
    }

    private static Attribute getAttribute(XMLStreamReader reader, String name) {
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
