public class RunningTrack implements Obstacle{
    private int size;
    private String name="Беговая дорожка";

    public RunningTrack(int size) {
        this.size = size;
    }

    public RunningTrack() {
        this(1000);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isCrossed(Being being){
        return being.getMaxDistance()>size;
    }
}
