package fun.gottagras.uhc.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fun.gottagras.uhc.Main;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class newCommand implements CommandExecutor {
    private Main main;
    public newCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        // CHECK
        if (main.uhc_created)
        {
            commandSender.sendMessage("§6Server need reload");
            return false;
        }

        // SEND PLAYERS TO LOBBY
        for (Player player:Bukkit.getServer().getOnlinePlayers())
        {
            final ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            player.sendPluginMessage(Main.INSTANCE, "BungeeCord", out.toByteArray());
        }

        // UHC STATE
        main.uhc_created = true;
        main.uhc_state = "waiting";

        // UNLOAD WORLD
        Bukkit.unloadWorld(Bukkit.getWorld("uhc"), true);
        Bukkit.unloadWorld(Bukkit.getWorld("uhc_nether"), true);
        Bukkit.unloadWorld(Bukkit.getWorld("uhc_the_end"), true);

        // LOAD WORLD
        WorldCreator worldCreator = new WorldCreator("uhc");
        worldCreator.environment(World.Environment.NORMAL);

        WorldCreator worldNetherCreator = new WorldCreator("uhc_nether");
        worldNetherCreator.environment(World.Environment.NETHER);

        WorldCreator worldTheEndCreator = new WorldCreator("uhc_the_end");
        worldTheEndCreator.environment(World.Environment.THE_END);

        if (strings.length > 0)
        {
            // SET SEED
            long seed = Long.parseLong(strings[0]);

            // DELETE WORLD
            File uhcFile = new File(System.getProperty("user.dir") + "\\uhc");
            main.fileDelete(uhcFile);
            File uhcNetherFile = new File(System.getProperty("user.dir") + "\\uhc_nether");
            main.fileDelete(uhcNetherFile);
            File uhcEndFile = new File(System.getProperty("user.dir") + "\\uhc_the_end");
            main.fileDelete(uhcEndFile);

            // SEED WORLD
            worldCreator.seed(seed);
            worldNetherCreator.seed(seed);
            worldTheEndCreator.seed(seed);

            // CREATION WORLD
            worldCreator.environment(World.Environment.NORMAL);
            worldNetherCreator.environment(World.Environment.NETHER);
            worldTheEndCreator.environment(World.Environment.THE_END);
        }
        Bukkit.createWorld(worldCreator);
        Bukkit.createWorld(worldNetherCreator);
        Bukkit.createWorld(worldTheEndCreator);

        World world = Bukkit.getWorld("uhc");
        World world_nether = Bukkit.getWorld("uhc_nether");
        World world_the_end = Bukkit.getWorld("uhc_the_end");

        world.setGameRuleValue("naturalRegeneration", "false");
        world_nether.setGameRuleValue("naturalRegeneration", "false");
        world_the_end.setGameRuleValue("naturalRegeneration", "false");

        return false;
    }
}
