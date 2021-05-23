package fun.gottagras.uhc.timers;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.listeners.goneFishing;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class uhcTime extends BukkitRunnable implements Runnable
{
    private Main main;
    public uhcTime(Main main)
    {
        this.main = main;
    }

    @Override
    public void run()
    {
        // TIME INVINCIBLE
        if (main.uhc_time == main.uhc_invincible)
        {
            main.uhc_state = "pve";
            Bukkit.broadcastMessage("§7Invincibilité désactivée");
        }

        // TIME PVP
        if (main.uhc_time == main.uhc_pvp)
        {
            main.uhc_state = "pvp";
            Bukkit.broadcastMessage("§7PvP activé");
            for (Player player: main.uhc_real_player_list)
            {
                if (player != null) player.setHealth(player.getHealthScale());
            }
        }

        // TIME BORDER
        if (main.uhc_time == main.uhc_border)
        {
            main.uhc_state = "border";
            Bukkit.broadcastMessage("§7Border en mouvement");

            WorldBorder worldBorder = Bukkit.getWorld("uhc").getWorldBorder();
            WorldBorder worldNetherBorder = Bukkit.getWorld("uhc_nether").getWorldBorder();
            WorldBorder worldTheEndBorder = Bukkit.getWorld("uhc_the_end").getWorldBorder();

            worldBorder.setSize(100, main.uhc_meetup- main.uhc_border);
            worldNetherBorder.setSize(100, main.uhc_meetup- main.uhc_border);
            worldTheEndBorder.setSize(100, main.uhc_meetup- main.uhc_border);
        }

        // TIME MEETUP
        if (main.uhc_time == main.uhc_meetup)
        {
            main.uhc_state = "meetup";
            Bukkit.broadcastMessage("§7Fin du mouvement de la border");
            Bukkit.getWorld("uhc").getWorldBorder().setSize(100);
            for (Player player: main.uhc_real_player_list)
            {
                if (player != null) player.giveExpLevels(667);
            }
        }

        // GONEFISHING
        if (main.uhc_format.equals("gonefishing"))
        {
            new goneFishing(main).skyHigh();
        }

        // CHECK WIN
        if (!main.win && !main.uhc_state.equals("start") && !main.uhc_state.equals("pve")) main.checkWin();

        main.uhc_time ++;
    }
}
