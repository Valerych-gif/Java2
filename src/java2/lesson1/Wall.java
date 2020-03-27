package java2.lesson1;

public class Wall implements Obstacle{
    private int size;
    private String name="Стена";

    public Wall(int size) {
        this.size = size;
    }

    public Wall() {
        this(80);
    }

    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isCrossed(Being being){
        return being.getMaxJump()>size;
    }
}
