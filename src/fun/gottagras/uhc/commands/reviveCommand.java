package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class reviveCommand implements CommandExecutor {
    private Main main;
    public reviveCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (main.uhc_state.equals("waiting")) return false;
        if (strings.length >= 1)
        {
            String reviveName = strings[0];
            Player revivePlayer = null;
            boolean isIn = false;
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (player.getDisplayName().equalsIgnoreCase(reviveName))
                {
                    revivePlayer = player;
                }
            }
            for (Player current_player: main.uhc_player_list)
            {
                if (revivePlayer == current_player)
                {
                    isIn = true;
                    break;
                }
            }
            if (!isIn && reviveName != null)
            {
                // SUMMON PLAYER
                assert revivePlayer != null;
                main.resetPlayer(revivePlayer);
                main.randomTp(revivePlayer);
                revivePlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 254), false);
                revivePlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 254), false);

                // GIVE STUFF
                switch (main.uhc_format)
                {
                    case "meetup":
                        new startCommand(main).giveMeetupStuff(revivePlayer);
                        break;

                    case "gonefishing":
                        new startCommand(main).giveGoneFishingStuff(revivePlayer);
                        break;

                    default:
                        new startCommand(main).giveUHCStuff(revivePlayer);
                        break;
                }

                // ADD TO THE LIST
                main.uhc_player_list[main.uhc_player_number] = revivePlayer;
                main.uhc_player_number ++;
                main.uhc_real_player_list[main.uhc_real_player_number] = revivePlayer;
                main.uhc_real_player_number ++;
                main.uhc_join_tracker ++;
            }
        }
        else commandSender.sendMessage("§6/revive <player>");
        return false;
    }
}
