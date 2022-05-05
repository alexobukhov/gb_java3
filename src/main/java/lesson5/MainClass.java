package lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass{
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final int CARS_COUNT = 4;
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Semaphore semaphore = new Semaphore(CARS_COUNT / 2);
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CountDownLatch countDownLatchFinishRace = new CountDownLatch(CARS_COUNT);
        CountDownLatch countDownLatchStartRace = new CountDownLatch(CARS_COUNT);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), semaphore, countDownLatchFinishRace,
                    countDownLatchStartRace, cyclicBarrier);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        countDownLatchStartRace.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatchFinishRace.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
