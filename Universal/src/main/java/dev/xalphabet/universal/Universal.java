package dev.xalphabet.universal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Universal extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("Universal has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("alert").setExecutor(this);
        getCommand("kickall").setExecutor(this);
        getCommand("hello").setExecutor(this);
        getCommand("time").setExecutor(this);
        getCommand("heal").setExecutor(this);
        getCommand("feed").setExecutor(this);
        getCommand("gamemode").setExecutor(this);
        getCommand("gmc").setExecutor(this);
        getCommand("gms").setExecutor(this);
        getCommand("gmsp").setExecutor(this);
        getCommand("gma").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Universal has been disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Get the player who joined
        String playerName = event.getPlayer().getName();

        // Display the welcome message on the player's screen idk why but sure.
        String welcomeMessage = ChatColor.BLUE + "Welcome to the Server!";
        String playerNameMessage = ChatColor.DARK_GRAY + "Have fun, " + playerName + "!";

        event.getPlayer().sendTitle(welcomeMessage, playerNameMessage, 10, 70, 20);

        event.getPlayer().sendMessage("Hello, " + playerName + "!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("alert")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            Player senderPlayer = (Player) sender;

            if (args.length < 1) {
                senderPlayer.sendMessage(ChatColor.RED + "Usage: /alert <message>");
                return true;
            }

            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(arg).append(" ");
            }

            String alertMessage = ChatColor.RED + message.toString().trim();

            // Send title and play sound to all online players for more action ig.
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(alertMessage, "", 10, 70, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);
            }

            return true;
        } else if (label.equalsIgnoreCase("kickall")) {
            if (!sender.hasPermission("universal.kickall")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            // Kick all players from the server
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.hasPermission("universal.kickall.bypass")) { // Check if player can bypass kick
                    player.kickPlayer(ChatColor.RED + "You have been kicked from the server by an administrator.");
                }
            }

            Bukkit.broadcastMessage(ChatColor.RED + "All players have been kicked from the server by an administrator.");
            return true;
        } else if (label.equalsIgnoreCase("hello")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            sender.sendMessage(ChatColor.GREEN + "Hello, " + sender.getName() + "!");
            return true;
        } else if (label.equalsIgnoreCase("time")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            Player player = (Player) sender;
            World world = player.getWorld();
            long time = world.getTime();
            String formattedTime = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

            sender.sendMessage(ChatColor.GREEN + "Current time: " + formattedTime);
            return true;
        } else if (label.equalsIgnoreCase("heal")) {
            if (!sender.hasPermission("universal.heal")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            Player player = (Player) sender;
            player.setHealth(player.getMaxHealth());
            player.sendMessage(ChatColor.GREEN + "You have been healed!");
            return true;
        } else if (label.equalsIgnoreCase("feed")) {
            if (!sender.hasPermission("universal.feed")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            Player player = (Player) sender;
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.GREEN + "You have been fed!");
            return true;
        } else if (label.equalsIgnoreCase("gamemode")) {
            if (!sender.hasPermission("universal.gamemode")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /gamemode <gamemode> <player>");
                return true;
            }

            GameMode mode;
            try {
                mode = GameMode.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + "Invalid gamemode! Available modes: SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }

            target.setGameMode(mode);
            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s gamemode to " + mode.toString().toLowerCase() + ".");
            return true;
        } else if (label.equalsIgnoreCase("gmc")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            if (!sender.hasPermission("universal.gamemode")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            Player player = (Player) sender;
            Player target = null;

            if (args.length >= 1) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
            } else {
                target = player;
            }

            target.setGameMode(GameMode.CREATIVE);
            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s gamemode to Creative.");
            return true;
        } else if (label.equalsIgnoreCase("gms")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            if (!sender.hasPermission("universal.gamemode")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            Player player = (Player) sender;
            Player target = null;

            if (args.length >= 1) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
            } else {
                target = player;
            }

            target.setGameMode(GameMode.SURVIVAL);
            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s gamemode to Survival.");
            return true;
        } else if (label.equalsIgnoreCase("gmsp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            if (!sender.hasPermission("universal.gamemode")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            Player player = (Player) sender;
            Player target = null;

            if (args.length >= 1) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
            } else {
                target = player;
            }

            target.setGameMode(GameMode.SPECTATOR);
            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s gamemode to Spectator.");
            return true;
        } else if (label.equalsIgnoreCase("gma")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }

            if (!sender.hasPermission("universal.gamemode")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            Player player = (Player) sender;
            Player target = null;

            if (args.length >= 1) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
            } else {
                target = player;
            }

            target.setGameMode(GameMode.ADVENTURE);
            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s gamemode to Adventure.");
            return true;
        }
        return false;
    }
}
