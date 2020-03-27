import java.util.Random;

public class Beings {
    public static void main(String[] args) {

        Wall wall = new Wall(60);

        Being[] beings = new Being[10];
        Obstacle[] obstacles = new Obstacle[10];

        Random rnd = new Random();
        int beingRnd;
        int humanCnt=1;
        int catCnt=1;
        int robotCnt=1;

        for (int i = 0; i < beings.length; i++) {
            beingRnd = rnd.nextInt(3);
            if (beingRnd%3==0){
                beings[i] = new Human("Человек №" + humanCnt++, rnd.nextInt(5000), rnd.nextInt(200));
            } else if (beingRnd%3==1) {
                beings[i] = new Cat("Кошак №" + catCnt++,  rnd.nextInt(1000), rnd.nextInt(200));
            } else {
                beings[i] = new Robot("Робот №" + robotCnt++,  rnd.nextInt(30000), rnd.nextInt(20));
            }
        }

        int obsRnd;
        for (int i = 0; i < obstacles.length; i++) {
            obsRnd = rnd.nextInt(2);
            if (obsRnd%2==0) {
                obstacles[i] = new Wall(rnd.nextInt(150));
            } else {
                obstacles[i] = new RunningTrack(rnd.nextInt(2000));
            }
        }

        String obsMsg;
        String failMsg;
        for (Being bng : beings) {
            obsMsg="";
            failMsg="";
            for (Obstacle obs : obstacles) {
                if (!obs.isCrossed(bng)){
                    failMsg+=String.format("%s не смог преодолеть препятствие %s (%s).", bng.getName(), obs.getName(), obs.getSize());
                    break;
                } else {
                    obsMsg+=String.format(", %s (%s)", obs.getName(), obs.getSize());
                }
            }
            if (!obsMsg.equals("")){
                System.out.print("Препятствия, которые преодолел "+bng.getName()+": "+obsMsg.substring(2)+". ");
            }
            System.out.println(failMsg);
        }
    }
}
