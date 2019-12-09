package threads;

import java.util.Random;

public class PurchaseOrdersThread implements Runnable{


    public void run() {
        System.out.println("PurchaseOrdersThread running...");

        while (true) {
            Random random = new Random();
            int price = random.nextInt(100); // de 0 a 99
            int n_units = random.nextInt(1000); //de 0 a 999

            System.out.println("Price generated: " + price);
            System.out.println("Number of units generated: " + n_units);

            try {
                Thread.sleep(5000); //Sleep de 5 segundos
            } catch (InterruptedException e) {



                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) {
        (new Thread(new PurchaseOrdersThread())).start();
    }


}
