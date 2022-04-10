package eu.syntaxxerror.deathchest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Deathchest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        int pluginId = 14890;
        Metrics metrics = new Metrics(this, pluginId);

        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("SpigotMC: https://www.spigotmc.org/members/etwasmagwurst_.1230382/");
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Block chest = Bukkit.getWorld(p.getWorld().getName()).getBlockAt(p.getLocation().getBlockZ(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());

        e.getDrops().clear();

        if(!p.getInventory().isEmpty()) {
            chest.setType(Material.CHEST);
            Chest c = (Chest) chest.getState();
            c.setCustomName(p.getName());
            for(ItemStack i : p.getInventory().getContents()) {
                assert i != null;
                if (i != null) {
                    c.getInventory().addItem(i);
                }
            }
        }
    }
}
