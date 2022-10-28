package me.mike3132.infinitebuckets.EventManager;

import me.mike3132.infinitebuckets.ItemManager.WaterBucket;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

public class WaterBucketEvent implements Listener {

    @EventHandler
    public void onWaterPlace(PlayerBucketEmptyEvent pie) {
        Player player = pie.getPlayer();
        Block block = pie.getBlock();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();
        WaterBucket waterBucketItem = new WaterBucket();
        ItemStack waterBucket = waterBucketItem.getWaterBucket();
        if (player.getInventory().getItemInMainHand().isSimilar(waterBucket)) {
            if (block.getBlockData() instanceof Waterlogged) {
                Waterlogged waterlogged = (Waterlogged) block.getBlockData();
                waterlogged.setWaterlogged(true);
                block.setBlockData((BlockData)waterlogged);
            } else {
                player.getWorld().getBlockAt(blockX, blockY, blockZ).setType(Material.WATER);
            }
            pie.setCancelled(true);
        }
    }
}
