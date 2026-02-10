package classique;

public class Main {
    public static void main(String[] args) {
        testMove();
        testItem();
        testHunter();
    }

    private static void testMove() {
        Grid g = new Grid();
        Player p = new Player("P", 5);
        place(g, p, 1, 1);

        System.out.println("MOVE start: (" + p.getPosition().getX() + "," + p.getPosition().getY() + ") E=" + p.getEnergy());
        p.move(g, g.getCell(2, 1));
        System.out.println("MOVE after: (" + p.getPosition().getX() + "," + p.getPosition().getY() + ") E=" + p.getEnergy());
        System.out.println();
    }

    private static void testItem() {
        Grid g = new Grid();
        Player p = new Player("P", 5);
        place(g, p, 1, 1);

        Cell c = g.getCell(2, 1);
        c.setItem(new Item(1, "B", Item.ItemType.BONUS, 3, false));

        System.out.println("ITEM before move: E=" + p.getEnergy() + " hasItem=" + c.hasItem());
        p.move(g, c);
        System.out.println("ITEM after move:  E=" + p.getEnergy() + " hasItem=" + c.hasItem());

        Cell d = g.getCell(3, 1);
        d.setItem(new Item(2, "M", Item.ItemType.MALUS, -4, false));
        p.move(g, d);
        System.out.println("ITEM after malus: E=" + p.getEnergy() + " pos=(" + p.getPosition().getX() + "," + p.getPosition().getY() + ")");
        System.out.println();
    }

    private static void testHunter() {
        Grid g = new Grid();
        Player man = new Player("Man", 5);
        Hunter h = new Hunter("H", 5);
        place(g, man, 4, 4);
        place(g, h, 4, 2);

        Cell target = g.getCell(7, 7);

        Cell choice1 = h.chooseMove(g, man, target);
        System.out.println("HUNTER choice (no adjacency): (" + choice1.getX() + "," + choice1.getY() + ")");

        man.move(g, g.getCell(4, 3));
        Cell choice2 = h.chooseMove(g, man, target);
        System.out.println("HUNTER choice (adjacent): (" + choice2.getX() + "," + choice2.getY() + ")");

        if (choice2 == man.getPosition()) h.eliminate(man);
        System.out.println("HUNTER eliminate: manEnergy=" + man.getEnergy() + " manPos=" + (man.getPosition() == null ? "null" : "notNull"));
        System.out.println();
    }

    private static void place(Grid g, Player p, int x, int y) {
        Cell c = g.getCell(x, y);
        c.setOccupiedBy(p);
        p.setPosition(c);
        if (p instanceof Hunter) g.setHunter(p); else g.setPlayer(p);
    }
}
