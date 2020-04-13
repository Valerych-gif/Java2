package java2.lesson5;

public class Threads {

    public static void main(String[] args) throws InterruptedException {
        workWithoutThreads();
        workWithThreads();
    }

    private static void workWithoutThreads() {

        final int SIZE = 10000000;
        float[] arr = new float[SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1.0f;
        }

        long timer = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        timer = System.currentTimeMillis() - timer;
        System.out.printf("Время выполнения программы в однопоточном режиме: %s миллисекунд\n", timer);

    }

    private static void workWithThreads() throws InterruptedException {

        final int SIZE = 10000000;
        final int HALF_SIZE = SIZE/2;
        float[] arr = new float[SIZE];
        float[] startArr = new float[HALF_SIZE];
        float[] endArr = new float[HALF_SIZE];

        long timer = System.currentTimeMillis();
        long splitTimer = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1.0f;
        }

        System.arraycopy(arr, 0, startArr, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, endArr, 0, HALF_SIZE);
        splitTimer =System.currentTimeMillis() - splitTimer;
        System.out.printf("Время разбивки массива на две половины: %s мсек\n", splitTimer);

        MathMachine mathMachine1 = new MathMachine(startArr, 0);
        Thread thread1 = new Thread(mathMachine1);
        thread1.start();
        startArr = mathMachine1.getArr();

        MathMachine mathMachine2 = new MathMachine(endArr, HALF_SIZE);
        Thread thread2 = new Thread(mathMachine2);
        thread2.start();
        endArr = mathMachine2.getArr();

        thread1.join();
        thread2.join();
        System.out.printf("Время обработки первой половины массива: %s мсек\n", mathMachine1.getTimer());
        System.out.printf("Время обработки второй половины массива: %s мсек\n", mathMachine2.getTimer());

        long mergeTimer = System.currentTimeMillis();
        System.arraycopy(startArr, 0, arr, 0, HALF_SIZE);
        System.arraycopy(endArr, 0, arr, HALF_SIZE, HALF_SIZE);
        mergeTimer = System.currentTimeMillis()-mergeTimer;
        System.out.printf("Время склейки двух массивов: %s мсек\n", mergeTimer);

        timer = System.currentTimeMillis() - timer;
        System.out.printf("Время выполнения программы в двухпоточном режиме: %s мсек\n", timer);

    }

    static class MathMachine implements Runnable {
        private float[] arr;
        private long timer;
        int startPosition;

        public MathMachine (float[] arr, int startPosition){
            this.arr=arr;
            this.startPosition=startPosition;
        }
        @Override
        public void run() {
            timer = System.currentTimeMillis();

            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + (i+startPosition) / 5) * Math.cos(0.2f + (i+startPosition) / 5) * Math.cos(0.4f + (i+startPosition) / 2));
            }
            timer = System.currentTimeMillis() - timer;
        }

        public float[] getArr() {
            return arr;
        }

        public long getTimer() {
            return timer;
        }
    }
}
