package me.mike3132.infinitebuckets.CommandManager;

import me.mike3132.infinitebuckets.ItemManager.LavaBucket;
import me.mike3132.infinitebuckets.ItemManager.WaterBucket;
import me.mike3132.infinitebuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InfiniteBuckets implements CommandExecutor {

    public InfiniteBuckets() {
        Main.plugin.getCommand("InfiniteBuckets").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 0) {
            if (!sender.hasPermission("InfiniteBuckets.Command")) {
                sender.sendMessage("No permission Placeholder");
                return true;
            }
            if (sender.hasPermission("InfiniteBuckets.Admin")) {
                switch (args[0].toUpperCase()) {
                    case "RELOAD":
                        if (!sender.hasPermission("InfiniteBuckets.Reload")) {
                            sender.sendMessage("No reload permission");
                        }
                        sender.sendMessage("Reload Placeholder");
                        // If they have perms do stuff
                        break;
                    case "GIVE":
                        if (!sender.hasPermission("InfiniteBuckets.Give")) {
                            sender.sendMessage("No give permission");
                        }
                        if (args.length != 1) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                if (args[2].equalsIgnoreCase("Water")) {
                                    WaterBucket waterBucketItem = new WaterBucket();
                                    ItemStack waterBucket = waterBucketItem.getWaterBucket();
                                    if (target.getInventory().firstEmpty() == -1) {
                                        target.sendMessage("Your inventory is full");
                                        // Drop items on the ground
                                        Location location = target.getLocation();
                                        location.getWorld().dropItem(location.add(0,1,0), waterBucket);
                                        return true;
                                    }
                                    target.getInventory().addItem(waterBucket);
                                    target.sendMessage("You have been given a bucket");
                                } else {
                                    if (args[2].equalsIgnoreCase("Lava")) {
                                        LavaBucket lavaBucketItem = new LavaBucket();
                                        ItemStack lavaBucket = lavaBucketItem.getLavaBucket();
                                        if (target.getInventory().firstEmpty() == -1) {
                                            target.sendMessage("Your inventory is full");
                                            // Drop items on the ground
                                            Location location = target.getLocation();
                                            location.getWorld().dropItem(location.add(0,1,0), lavaBucket);
                                            return true;
                                        }
                                        target.getInventory().addItem(lavaBucket);
                                        target.sendMessage("You have been given a bucket");
                                    } else {
                                        sender.sendMessage("Please select either water or lava");
                                    }
                                }
                            } else {
                                sender.sendMessage("Player not found message placeholder");
                            }
                        } else {
                            sender.sendMessage("Please select a player");
                        }
                        break;
                    default:
                        sender.sendMessage("Default Message");
                        break;
                }
            }

        }

        return true;
    }
}
