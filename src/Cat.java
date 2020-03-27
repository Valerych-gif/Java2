public class Cat implements Being{
    private int maxDistance;
    private int maxJump;
    private String name;

    public Cat(String name, int maxDistance, int maxJump) {
        this.name=name;
        this.maxDistance = maxDistance;
        this.maxJump = maxJump;
    }

    public Cat(String name) {
        this(name, 500, 200);
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
        System.out.printf("Кот по кличке %s прыгнул на %d сантиметров%n", name, maxJump);
    }

    @Override
    public void run() {
        System.out.printf("Кот по кличке %s пробежал на %d метров%n", name, maxDistance);
    }
}
