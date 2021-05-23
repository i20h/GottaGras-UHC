package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class waitingListener implements Listener {
    private Main main;
    public waitingListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            Location spawn = new Location(Bukkit.getWorld("world"), 0, 255, 0);
            player.teleport(spawn);
            main.resetPlayer(player);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            event.setCancelled(event.getCause() != EntityDamageEvent.DamageCause.VOID);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            player.setFoodLevel(20);
        }
    }
}
