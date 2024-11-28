package multithreading;

public class Worker extends Thread{
    private Object mutex1;
    private Object mutex2;
    private String srcName1;
    private String srcName2;

    public Worker(String srcName1, Object mutex1, String srcName2, Object mutex2) {
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
        this.srcName1 = srcName1;
        this.srcName2 = srcName2;
    }

    @Override
    public void run() {
        synchronized (mutex1) {
            System.out.println("Locking %s by %s".formatted(srcName1, getName()));
            synchronized (mutex2) {
                System.out.println("Locking %s by %s".formatted(srcName2, getName()));
                System.out.println("Unlocking %s by %s".formatted(srcName2, getName()));
            }
            System.out.println("Unlocking %s by %s".formatted(srcName1, getName()));
        } 
    }
}