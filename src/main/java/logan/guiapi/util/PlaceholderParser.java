package logan.guiapi.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.function.Function;

/**
 *
 * @author Tre Logan
 */
public class PlaceholderParser {
    
    public static final String PLAYER_NAME = "%player_name%";
    public static final String PLAYER_COUNT = "%player_count%";
    
    private static Function<Player, String> nameFun;
    private static Function<Collection<? extends Player>, String> countFun;
    
    static {
        nameFun = Player::getName;
        countFun = (coll) -> String.valueOf(coll.size());
    }
    
    public static String parse(String str, Player player) {
        
        if (str == null) {
            return str;
        }
        
        if (str.contains(PLAYER_NAME)) {
            str = str.replace(PLAYER_NAME, nameFun.apply(player));
        }
        
        if (str.contains(PLAYER_COUNT)) {
            str = str.replace(PLAYER_COUNT, countFun.apply(Bukkit.getOnlinePlayers()));
        }
        
        return str;
    }
    
}
