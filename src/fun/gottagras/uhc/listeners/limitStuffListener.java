package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Map;

public class limitStuffListener implements Listener {
    private Main main;
    public limitStuffListener(Main main)
    {
        this.main = main;
    }

    public void limitEnchant(ItemStack itemStack)
    {
        if (itemStack == null) return;
        ItemMeta itemMeta = itemStack.getItemMeta();
        Material material = itemStack.getType();
        // SWORD
        if (material == Material.DIAMOND_SWORD || material == Material.IRON_SWORD || material == Material.STONE_SWORD || material == Material.GOLD_SWORD || material == Material.WOOD_SWORD)
        {
            if (itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+1)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+2)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+3)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+4)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+5)))
            {
                itemMeta.removeEnchant(Enchantment.DAMAGE_ALL);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, main.uhc_stuffLimit_sharpness, true);
                itemStack.setItemMeta(itemMeta);
            }
            if (!main.uhc_stuffLimit_fireAspect)
            {
                if (itemMeta.toString().contains("FIRE_ASPECT"))
                {
                    itemMeta.removeEnchant(Enchantment.FIRE_ASPECT);
                    itemStack.setItemMeta(itemMeta);
                }
            }
        }
        // DIAMOND ARMOR
        else if (material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS)
        {
            if (itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+1)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+2)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+3)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+4)))
            {
                itemMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, main.uhc_stuffLimit_diamondProtection, true);
                itemStack.setItemMeta(itemMeta);
            }
        }
        // IRON ARMOR
        else if (material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS)
        {
            if (itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+1)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+2)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+3)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+4)))
            {
                itemMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, main.uhc_stuffLimit_ironProtection, true);
                itemStack.setItemMeta(itemMeta);
            }
        }
        // BOW
        else if (material == Material.BOW)
        {
            if (itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+1)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+2)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+3)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+4)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+5)))
            {
                itemMeta.removeEnchant(Enchantment.ARROW_DAMAGE);
                itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, main.uhc_stuffLimit_power, true);
                itemStack.setItemMeta(itemMeta);
            }
            if (!main.uhc_stuffLimit_flame)
            {
                if (itemMeta.toString().contains("ARROW_FIRE"))
                {
                    itemMeta.removeEnchant(Enchantment.ARROW_FIRE);
                    itemStack.setItemMeta(itemMeta);
                }
            }
        }
    }

    public boolean notchApple(ItemStack itemStack)
    {
        if (itemStack == null) return false;
        return itemStack.getType() == Material.GOLDEN_APPLE && itemStack.getData().toString().equals("GOLDEN_APPLE(1)") && !main.uhc_stuffLimit_notchApple;
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        limitEnchant(event.getItem().getItemStack());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        limitEnchant(event.getItemDrop().getItemStack());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        limitEnchant(event.getItem());
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) event.setCancelled(notchApple(event.getItem()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        limitEnchant(event.getCurrentItem());
        if (event.getAction() == InventoryAction.PLACE_ALL || event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
        {
            event.getCurrentItem();
        }
    }
}
