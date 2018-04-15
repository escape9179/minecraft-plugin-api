package logan.guiapi.util;

import java.util.Collection;
import java.util.function.Function;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Tre Logan
 */
public class PlaceholderParser {
    
    public static final String PLAYER_NAME = "%player_name%";
    public static final String PLAYER_COUNT = "%player_count%";
    
    private static Function<Player, String> nameFunction;
    private static Function<Collection<? extends Player>, String> countFunction;
    
    static {
        nameFunction = Player::getName;
        countFunction = (coll) -> String.valueOf(coll.size());
    }
    
    public static String parse(String str, Player player) {
        
        if (str.contains(PLAYER_NAME)) {
            str = str.replace(PLAYER_NAME, nameFunction.apply(player));
        }
        
        if (str.contains(PLAYER_COUNT)) {
            str = str.replace(PLAYER_COUNT, countFunction.apply(Bukkit.getOnlinePlayers()));
        }
        
        return str;
    }
    
}
