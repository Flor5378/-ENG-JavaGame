package classique;

public class Item {
    public enum ItemType { BONUS, MALUS }

    private int id;
    private String name;
    private ItemType type;
    private int energyDelta;
    private boolean renewable;

    public Item(int id, String name, ItemType type, int energyDelta, boolean renewable) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.energyDelta = energyDelta;
        this.renewable = renewable;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public ItemType getType() { return type; }
    public int getEnergyDelta() { return energyDelta; }
    public boolean isRenewable() { return renewable; }

    public void applyTo(Player player) {
        if (player == null) return;
        player.setEnergy(player.getEnergy() + energyDelta);
        if (player.getEnergy() < 0) player.setEnergy(0);
    }

    public boolean isBonus() { return type == ItemType.BONUS; }
    public boolean isMalus() { return type == ItemType.MALUS; }
}
