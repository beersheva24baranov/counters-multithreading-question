package multithreading;

public class Worker extends Thread{
    private Object mutex1;
    private Object mutex2;
    private String src1;
    private String src2;

    public Worker(String src1, Object mutex1, String src2, Object mutex2) {
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
        this.src1 = src1;
        this.src2 = src2;
    }

    @Override
    public void run() {
        synchronized (mutex1) {
            System.out.println("Locking %s by %s".formatted(src1, getName()));
            synchronized (mutex2) {
                System.out.println("Locking %s by %s".formatted(src2, getName()));
                System.out.println("Unlocking %s by %s".formatted(src2, getName()));
            }
            System.out.println("Unlocking %s by %s".formatted(src1, getName()));
        } 
    }
}