package lesson4;

public class Main {

    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Main mainClass = new Main();
        Thread threadA = new Thread(() -> {
            mainClass.printA();
        });
        Thread threadB = new Thread(() -> {
            mainClass.printB();
        });
        Thread threadC = new Thread(() -> {
            mainClass.printC();
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public void printA() {
        synchronized (monitor) {
            try {
                monitor.wait(1000);
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("А");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    if (i == 4) {
                        break;
                    } else {
                        System.out.print("C");
                        currentLetter = 'A';
                    }
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("C");
    }
}
