package net.poollovernathan.brewery;
import net.minecraft.item.Item;

class EnergyItem extends Item implements IEnergyItem {
  private int energy;
  private int maxEnergy;

  public EnergyItem(Settings settings) {
    super(settings);
  }

  public int insertEnergy(int amount, boolean dry) {
    int energyLeft = maxEnergy - energy;
    if (amount > energyLeft) amount = energyLeft;
    if (!dry) energy += amount;
    return amount;
  }
  public int extractEnergy(int amount, boolean dry) {
    if (amount > energy) amount = energy;
    if (!dry) energy -= amount;
    return amount;
  }
  public boolean insertEnergyBlock(int amount, boolean dry) {
    int energyLeft = maxEnergy - energy;
    if (amount > energyLeft) return false;
    if (!dry) energy += amount;
    return true;
  }
  public boolean extractEnergyBlock(int amount, boolean dry) {
    if (amount > energy) return false;
    if (!dry) energy -= amount;
    return true;
    
  }
  public int transferEnergy(IEnergyItem destination, boolean dry) {
    int transfer = destination.peekEnergy() - destination.getMaxEnergy();
    if (transfer > energy) transfer = energy;
    transfer = destination.insertEnergy(transfer, dry);
    extractEnergy(transfer, dry);
    return transfer;
  }
  public int peekEnergy() {
    return energy;
  }
  public int getMaxEnergy() {
    return maxEnergy;
  }
}
