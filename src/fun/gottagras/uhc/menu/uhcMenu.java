package fun.gottagras.uhc.menu;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class uhcMenu implements Listener
{
    private Main main;
    public uhcMenu(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event)
    {
        Inventory inventory = event.getInventory();
        if (inventory.getName().equals(menu().getName()))
        {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (player.isOp())
            {
                ItemStack itemStack = event.getCurrentItem();
                if (itemStack != null)
                {
                    if (itemStack.getType() != Material.AIR)
                    {
                        // NAME
                        String itemName = itemStack.getItemMeta().getDisplayName();
                        String formatName = format().getItemMeta().getDisplayName();
                        String cutCleanName = cutClean().getItemMeta().getDisplayName();
                        String allTreeDropName = allTreeDrop().getItemMeta().getDisplayName();
                        String hasteyBoyName = hasteyBoy().getItemMeta().getDisplayName();

                        // CHANGES
                        if (itemName.equals(formatName))
                        {
                            switch (main.uhc_format) {
                                case "classico":
                                    // FORMAT
                                    main.uhc_format = "meetup";
                                    break;
                                case "meetup":
                                    main.uhc_format = "gonefishing";
                                    break;
                                case "gonefishing":
                                    // FORMAT
                                    main.uhc_format = "classico";
                                    break;
                            }
                        }
                        else if (itemName.equals(cutCleanName))
                        {
                            main.uhc_cutclean = !main.uhc_cutclean;
                        }
                        else if (itemName.equals(allTreeDropName))
                        {
                            main.uhc_alltreedrop = !main.uhc_alltreedrop;
                        }
                        else if (itemName.equals(hasteyBoyName))
                        {
                            main.uhc_hasteyboy = !main.uhc_hasteyboy;
                        }
                    }
                }
            }
            player.openInventory(menu());
        }
    }

    public Inventory menu()
    {
        Inventory inventory = Bukkit.createInventory(null, 27, "§7UHC");
        inventory.setItem(0, format());
        inventory.setItem(10, cutClean());
        inventory.setItem(11, allTreeDrop());
        inventory.setItem(12, hasteyBoy());
        return inventory;
    }

    public ItemStack format()
    {
        ItemStack itemStack = new ItemStack(Material.GRASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Mode: §6"+main.uhc_format);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack cutClean()
    {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7CutClean: §6" + main.uhc_cutclean);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack allTreeDrop()
    {
        ItemStack itemStack = new ItemStack(Material.SAPLING);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7AllTreeDrop: §6" + main.uhc_alltreedrop);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack hasteyBoy()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7HasteyBoy: §6" + main.uhc_hasteyboy);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
