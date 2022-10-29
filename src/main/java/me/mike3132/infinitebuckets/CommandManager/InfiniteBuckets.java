package me.mike3132.infinitebuckets.CommandManager;

import me.mike3132.infinitebuckets.ItemManager.LavaBucket;
import me.mike3132.infinitebuckets.ItemManager.WaterBucket;
import me.mike3132.infinitebuckets.Main;
import me.mike3132.infinitebuckets.MessageManager.Messages;
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
                sender.sendMessage("No-permission-Placeholder");
                return true;
            }
            if (sender.hasPermission("InfiniteBuckets.Admin")) {
                switch (args[0].toUpperCase()) {
                    case "RELOAD":
                        if (!sender.hasPermission("InfiniteBuckets.Reload")) {
                            sender.sendMessage("No-reload-permission");
                        }
                        sender.sendMessage(Main.chatColor("&a[&cInfinite Buckets&a] " + "&6Config reloaded in &2" + String.valueOf(System.currentTimeMillis() - 1 + " &6ms")));
                        Main.plugin.reloadConfig();
                        break;
                    case "HELP":
                        Messages.sendConsoleMessage(sender, "Help-Header");
                        Messages.sendConsoleMessage(sender, "Help-A");
                        Messages.sendConsoleMessage(sender, "Help-B");
                        Messages.sendConsoleMessage(sender, "Help-C");
                        Messages.sendConsoleMessage(sender, "Help-D");
                        Messages.sendConsoleMessage(sender, "Help-E");
                        Messages.sendConsoleMessage(sender, "Help-Footer");
                        break;
                    case "GIVE":
                        if (!sender.hasPermission("InfiniteBuckets.Give")) {
                            Messages.sendConsoleMessage(sender, "No-give-permission");
                        }
                        if (args.length != 1) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                if (args.length > 2) {
                                    if (args[2].equalsIgnoreCase("Water")) {
                                        WaterBucket waterBucketItem = new WaterBucket();
                                        ItemStack waterBucket = waterBucketItem.getWaterBucket();
                                        if (target.getInventory().firstEmpty() == -1) {
                                            Messages.sendPlayerMessage(target, "You-have-been-given-a-water-bucket");
                                            Messages.sendPlayerMessage(target, "Your-inventory-is-full");
                                            Location location = target.getLocation();
                                            location.getWorld().dropItem(location.add(0, 1, 0), waterBucket);
                                            return true;
                                        }
                                        target.getInventory().addItem(waterBucket);
                                        Messages.sendPlayerMessage(target, "You-have-been-given-a-water-bucket");
                                    } else {
                                        if (args[2].equalsIgnoreCase("Lava")) {
                                            LavaBucket lavaBucketItem = new LavaBucket();
                                            ItemStack lavaBucket = lavaBucketItem.getLavaBucket();
                                            if (target.getInventory().firstEmpty() == -1) {
                                                Messages.sendPlayerMessage(target, "You-have-been-given-a-lava-bucket");
                                                Messages.sendPlayerMessage(target, "Your-inventory-is-full");
                                                Location location = target.getLocation();
                                                location.getWorld().dropItem(location.add(0, 1, 0), lavaBucket);
                                                return true;
                                            }
                                            target.getInventory().addItem(lavaBucket);
                                            Messages.sendPlayerMessage(target, "You-have-been-given-a-lava-bucket");
                                        }
                                    }
                                } else {
                                    Messages.sendConsoleMessage(sender, "Please-select-either-water-or-lava");
                                }
                            } else {
                                Messages.sendConsoleMessage(sender, "Player-not-found-message-placeholder");
                            }

                        } else {
                            Messages.sendConsoleMessage(sender, "Please-select-a-player");
                        }
                        break;
                    default:
                        Messages.sendConsoleMessage(sender, "Help-Trigger");
                        break;
                }
            }

        } else {
            Messages.sendConsoleMessage(sender, "Help-Trigger");
        }

        return true;
    }
}
