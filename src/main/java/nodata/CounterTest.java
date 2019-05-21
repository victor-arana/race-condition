package nodata;

public class CounterTest {

    public static void main(String[] args) throws InterruptedException {

        Counter myCounter = new Counter();

        Thread thread1 = new Thread(new CounterIncrementer(myCounter));
        thread1.setName("add thread");
        thread1.start();

        Thread thread2 = new Thread(new CounterIncrementer(myCounter));
        thread2.setName("add thread2");
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(myCounter.value());

    }
}

class CounterIncrementer implements Runnable{

    private Counter counter;

    public CounterIncrementer(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++){
                counter.increment();
        }
    }
}

class Counter {
    private volatile int c = 0;

    public void increment(){
        c++;
    }

    public void decrement(){
        c--;
    }

    public int value(){
        return c;
    }
}

