package lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static volatile boolean isWinner;
    static {
        CARS_COUNT = 0;
        isWinner = false;
    }
    private Race race;
    private int speed;
    private String name;
    private Semaphore semaphore;
    private CountDownLatch countDownLatchFinishRace;
    private CountDownLatch countDownLatchStartRace;
    private CyclicBarrier cyclicBarrier;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, Semaphore semaphore, CountDownLatch countDownLatchFinishRace,
               CountDownLatch countDownLatchStartRace, CyclicBarrier cyclicBarrier) {
        this.race = race;
        this.speed = speed;
        this.semaphore = semaphore;
        this.countDownLatchStartRace = countDownLatchStartRace;
        this.countDownLatchFinishRace = countDownLatchFinishRace;
        this.cyclicBarrier = cyclicBarrier;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cyclicBarrier.await();
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            countDownLatchStartRace.countDown();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
                try {
                    if (race.getStages().get(i) instanceof Tunnel) {
                        semaphore.acquire();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
            if (!isWinner) {
                isWinner = true;
                System.out.println(this.name + " -  WIN");
            }
            countDownLatchFinishRace.countDown();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
