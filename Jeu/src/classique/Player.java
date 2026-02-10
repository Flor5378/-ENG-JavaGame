package classique;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static int NEXT_ID = 1;

    private int id;
    private String name;
    private int energy;
    private Cell position;
    private List<Cell> visibleCells = new ArrayList<Cell>();

    public Player(String name, int energy) {
        this.id = NEXT_ID++;
        this.name = name;
        this.energy = energy;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }
    public Cell getPosition() { return position; }
    public void setPosition(Cell position) { this.position = position; }
    public List<Cell> getVisibleCells() { return visibleCells; }

    public List<Cell> getAdjacent(Grid grid, Cell from) {
        List<Cell> r = new ArrayList<Cell>();
        if (from == null) return r;
        int x = from.getX(), y = from.getY();
        addIfInside(grid, r, x + 1, y);
        addIfInside(grid, r, x - 1, y);
        addIfInside(grid, r, x, y + 1);
        addIfInside(grid, r, x, y - 1);
        return r;
    }

    private void addIfInside(Grid grid, List<Cell> r, int x, int y) {
        if (x >= 0 && y >= 0 && x < grid.getWidth() && y < grid.getHeight()) r.add(grid.getCell(x, y));
    }

    public void move(Grid grid, Cell target) {
        if (grid == null || target == null || energy <= 0) return;
        if (position != null && !position.isAdjacentTo(target)) return;
        if (target.getOccupiedBy() != null) return;

        if (position != null) position.setOccupiedBy(null);
        target.setOccupiedBy(this);
        position = target;

        energy--;

        if (target.hasItem()) {
            applyItem(target.getItem());
            if (!target.getItem().isRenewable()) target.removeItem();
        }

        visibleCells = getAdjacent(grid, position);
    }

    public void applyItem(Item item) {
        if (item != null) item.applyTo(this);
    }

    public boolean isAdjacentTo(Cell cell) {
        return position != null && position.isAdjacentTo(cell);
    }

    public boolean canMove() {
        return energy > 0;
    }
}
