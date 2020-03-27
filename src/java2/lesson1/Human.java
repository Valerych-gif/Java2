package java2.lesson1;

public class Human implements Being{

    private int maxDistance;
    private int maxJump;
    private String name;

    public Human(String name, int maxDistance, int maxJump) {
        this.name=name;
        this.maxDistance = maxDistance;
        this.maxJump = maxJump;
    }

    public Human(String name) {
        this(name, 300, 50);
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
        System.out.printf("Человек по имени %s прыгнул на %d сантиметров%n", name, maxJump);
    }

    @Override
    public void run() {
        System.out.printf("Человек по имени %s пробежал на %d метров%n", name, maxDistance);
    }
}
