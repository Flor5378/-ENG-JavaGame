package classique;

import java.util.List;

public class Hunter extends Player {
    public Hunter(String name, int energy) {
        super(name, energy);
    }

    public Cell chooseMove(Grid grid, Player man, Cell target) {
        if (grid == null || getPosition() == null) return null;
        if (man != null && man.getPosition() != null && getPosition().isAdjacentTo(man.getPosition())) return man.getPosition();

        List<Cell> adj = getAdjacent(grid, getPosition());
        Cell best = null;
        int bestD = Integer.MAX_VALUE;

        for (int i = 0; i < adj.size(); i++) {
            Cell c = adj.get(i);
            if (c.getOccupiedBy() != null) continue;
            int d = dist(c, target);
            if (d < bestD) { bestD = d; best = c; }
        }
        return best;
    }

    private int dist(Cell a, Cell b) {
        if (a == null || b == null) return Integer.MAX_VALUE;
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public void eliminate(Player man) {
        if (man == null) return;
        if (man.getPosition() != null) man.getPosition().setOccupiedBy(null);
        man.setEnergy(0);
        man.setPosition(null);
    }
}
