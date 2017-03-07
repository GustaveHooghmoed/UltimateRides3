package net.ryanhecht.rides;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import me.cristaling.UltimateRides.UltimateRides;
import me.cristaling.UltimateRides.Utils.Moveable;
import me.cristaling.UltimateRides.moveables.CustomPath;
import me.cristaling.UltimateRides.moveables.EntityMove;

public class RyanRide extends Moveable {

  CustomPath path;
  UltimateRides plugin;
  Player player;
  ArmorStand as;
  EntityMove mover;

  public RyanRide(Player player, UltimateRides plugin) {
    this.plugin = plugin;
    //    System.out.println(plugin);
    //    System.out.println(plugin.getRideManager());
    //    System.out.println(plugin.getRideManager().creatingPath);
    //    System.out.println(plugin.getRideManager().creatingPath.get(player));
    path = plugin.getRideManager().creatingPath.get(player).build();
    this.as = (ArmorStand) player.getWorld().spawnEntity(path.getOrigin().toLocation(player.getWorld()), EntityType.ARMOR_STAND);
    as.setHelmet(new ItemStack(Material.GOLD_BLOCK));
    mover = new EntityMove(path.getOrigin(), as);
    path.addChild(mover);
  }
  @Override
  public void moveTo(Vector vector) {

  }

  @Override
  public void runTick(boolean started) {
    path.runTick(started);
    Vector v = path.getPathPosition(path.getChildrenMap().get(mover));

    EulerAngle headAngle = new EulerAngle(v.getY(), v.getX(), 0);
    as.setHeadPose(headAngle);
  }

  @Override
  public void addChild(Moveable child) {


  }

}
