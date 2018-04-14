# gui-api
A Minecraft API to create GUIs with ease.

### Creating GUI using `MenuItemBuilder`
```
MenuItemBuilder mib = new MenuItemBuilder()
.setMaterial(Material.WOOL);
.setDurability(1);
.setName(ChatColor.GOLD + "Hello World!");
.setLore("Lorem ipsum dolor sit amet", "consectetur adipiscing elit");
.setMagic(true); // Magic enchants the item and removes the name
             
MenuItem menuItem = mib.build();
// Adding a click listener to your menu item
menuItem.addListener(e -> e.getPlayer().sendMessage("You clicked me!"));
        
Menu menu = new Menu(this); // Takes JavaPlugin as argument
menu.addItem(0, menuItem);
```
### Creating GUI using XML
```
<root>
    <menu id="menu" title="My Menu" rows="1">
        <item id="wool" name="^6Hello World!" material="wool" durability="1" magic="true"
              lore="Lorem ipsum dolor sit amet,consectetur adipiscing elit"></item>
    </menu>
</root>
```
```
GUILoader loader = new GUILoader(getClass());
MenuItem menuItem = loader.loadItemFromXML("wool", "/menu.xml"); // '/' indicates same directory as plugin.yml
Menu menu = loader.loadMenuFromXML("menu", "/menu.xml");
        
menuItem.addListener(e -> e.getPlayer().sendMessage("Clicked!"));
menu.addItem(0, menuItem);
```
### Show a player the menu
`menu.show(player);`
