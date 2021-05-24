package fun.gottagras.uhc.menu;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class limitStuffMenu implements Listener {
    private Main main;
    public limitStuffMenu(Main main)
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
                        String diamondArmorName = diamondArmor().getItemMeta().getDisplayName();
                        String diamondProtectionName = diamondProtection().getItemMeta().getDisplayName();
                        String ironArmorName = ironArmor().getItemMeta().getDisplayName();
                        String ironProtectionName = ironProtection().getItemMeta().getDisplayName();
                        String sharpnessName = sharpness().getItemMeta().getDisplayName();
                        String fireAspectName = fireAspect().getItemMeta().getDisplayName();
                        String powerName = power().getItemMeta().getDisplayName();
                        String flameName = flame().getItemMeta().getDisplayName();
                        String notchAppleName = notchApple().getItemMeta().getDisplayName();

                        if (itemName == null) return;

                        if (itemName.equals(diamondArmorName))
                        {
                            if (main.uhc_stuffLimit_diamondArmor == 4) main.uhc_stuffLimit_diamondArmor = 0;
                            else main.uhc_stuffLimit_diamondArmor++;
                        }
                        else if (itemName.equals(diamondProtectionName))
                        {
                            if (main.uhc_stuffLimit_diamondProtection == 4) main.uhc_stuffLimit_diamondProtection = 1;
                            else main.uhc_stuffLimit_diamondProtection++;
                        }
                        else if (itemName.equals(ironArmorName))
                        {
                            if (main.uhc_stuffLimit_ironArmor == 4) main.uhc_stuffLimit_ironArmor = 0;
                            else main.uhc_stuffLimit_ironArmor++;
                        }
                        else if (itemName.equals(ironProtectionName))
                        {
                            if (main.uhc_stuffLimit_ironProtection == 4) main.uhc_stuffLimit_ironProtection = 1;
                            else main.uhc_stuffLimit_ironProtection++;
                        }
                        else if (itemName.equals(sharpnessName))
                        {
                            if (main.uhc_stuffLimit_sharpness == 5) main.uhc_stuffLimit_sharpness = 1;
                            else main.uhc_stuffLimit_sharpness++;
                        }
                        else if (itemName.equals(fireAspectName))
                        {
                            main.uhc_stuffLimit_fireAspect = !main.uhc_stuffLimit_fireAspect;
                        }
                        else if (itemName.equals(powerName))
                        {
                            if (main.uhc_stuffLimit_power == 5) main.uhc_stuffLimit_power = 1;
                            else main.uhc_stuffLimit_power++;
                        }
                        else if (itemName.equals(flameName))
                        {
                            main.uhc_stuffLimit_flame = !main.uhc_stuffLimit_flame;
                        }
                        else if (itemName.equals(notchAppleName))
                        {
                            main.uhc_stuffLimit_notchApple = !main.uhc_stuffLimit_notchApple;
                        }
                    }
                }
            }
            player.openInventory(menu());
        }
    }

    public Inventory menu()
    {
        Inventory inventory = Bukkit.createInventory(null, 45, "§3Limite de Stuff");
        inventory.setItem(10, diamondArmor());
        inventory.setItem(11, diamondProtection());

        inventory.setItem(15, sharpness());
        inventory.setItem(16, fireAspect());

        inventory.setItem(19, ironArmor());
        inventory.setItem(20, ironProtection());

        inventory.setItem(22, notchApple());

        inventory.setItem(24, power());
        inventory.setItem(25, flame());

        return inventory;
    }

    public ItemStack diamondArmor()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE, main.uhc_stuffLimit_diamondArmor);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Pièce en Diamant: §6" + main.uhc_stuffLimit_diamondArmor);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack diamondProtection()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Protection max sur le Diamant: §6" + main.uhc_stuffLimit_diamondProtection);
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, main.uhc_stuffLimit_diamondProtection, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack ironArmor()
    {
        ItemStack itemStack = new ItemStack(Material.IRON_CHESTPLATE, main.uhc_stuffLimit_ironArmor);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Pièce en Fer: §6" + main.uhc_stuffLimit_ironArmor);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack ironProtection()
    {
        ItemStack itemStack = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Protection max sur le Fer: §6" + main.uhc_stuffLimit_ironProtection);
        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, main.uhc_stuffLimit_ironProtection, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack sharpness()
    {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Sharpness Max: §6" + main.uhc_stuffLimit_sharpness);
        itemMeta.addEnchant(Enchantment.DAMAGE_ALL, main.uhc_stuffLimit_sharpness, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack fireAspect()
    {
        ItemStack itemStack = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7FireAspect: §6" + main.uhc_stuffLimit_fireAspect);
        if (main.uhc_stuffLimit_fireAspect) itemMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack power()
    {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Power Max: §6" + main.uhc_stuffLimit_power);
        itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, main.uhc_stuffLimit_power, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack flame()
    {
        ItemStack itemStack = new ItemStack(Material.FIREBALL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Flame: §6" + main.uhc_stuffLimit_flame);
        if (main.uhc_stuffLimit_flame) itemMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack notchApple()
    {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Notch Apple: §6" + main.uhc_stuffLimit_notchApple);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
