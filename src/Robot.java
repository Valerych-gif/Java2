public class Robot implements Being{
    private int maxDistance;
    private int maxJump;
    private String name;

    public Robot(String name, int maxDistance, int maxJump) {
        this.name=name;
        this.maxDistance = maxDistance;
        this.maxJump = maxJump;
    }

    public Robot(String name) {
        this(name, 30000, 20);
    }

    @Override
    public int getMaxDistance() {
        return maxDistance;
    }

    @Override
    public int getMaxJump() {
        return maxJump;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jump() {
        System.out.printf("Робот с индексом %s прыгнул на %d сантиметров%n", name, maxJump);
    }

    @Override
    public void run() {
        System.out.printf("Робот с индексом %s пробежал на %d метров%n", name, maxDistance);
    }
}
