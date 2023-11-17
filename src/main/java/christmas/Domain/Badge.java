package christmas.Domain;

public enum Badge {
    NONE(0, "없음"),
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final Integer boundary;
    private final String name;


    Badge(Integer boundary, String name) {
        this.boundary = boundary;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadgeByBenefit(Integer benefit) {
        if (benefit >= SANTA.boundary) {
            return SANTA;
        }
        if (benefit >= TREE.boundary) {
            return TREE;
        }
        if (benefit >= STAR.boundary) {
            return STAR;
        }
        return NONE;
    }
}
