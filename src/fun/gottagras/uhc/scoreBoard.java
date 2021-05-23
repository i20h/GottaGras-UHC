package fun.gottagras.uhc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

public class scoreBoard extends BukkitRunnable implements Runnable {
    private Main main;
    public scoreBoard(Main main)
    {
        this.main = main;
    }

    @Override
    public void run()
    {
        // SCOREBOARD CREATION
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("GottaGras-UHC", "ScoreBoard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("GottaGras-UHC");

        // PLAYER ALIVE
        Score info_1 = objective.getScore("§6Joueurs en vie: §7"+ main.uhc_real_player_number);
        info_1.setScore(1);

        // TIME PLAYED
        Score info_2 = objective.getScore("§6Temps de jeu: §7" + main.uhc_time/60 + "min " + main.uhc_time%60 + "s");
        info_2.setScore(2);

        // BORDER SIZE
        if (main.uhc_created)
        {
            double size = Bukkit.getWorld("uhc").getWorldBorder().getSize()/2;
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            Score info_3 = objective.getScore("§6Border: §7"+ numberFormat.format(size));
            info_3.setScore(3);
        }

        // FORMAT
        Score info_4 = objective.getScore("§6Mode de jeu: §7"+ main.uhc_format);
        info_4.setScore(4);

        // SET SCOREBOARD
        for (Player player:Bukkit.getOnlinePlayers())
        {
            player.setScoreboard(board);
        }
    }
}
