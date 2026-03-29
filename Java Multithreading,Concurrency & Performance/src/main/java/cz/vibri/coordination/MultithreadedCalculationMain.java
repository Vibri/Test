package cz.vibri.coordination;

import java.math.BigInteger;

public class MultithreadedCalculationMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calculateResult(BigInteger.valueOf(10), BigInteger.valueOf(2),BigInteger.valueOf(10), BigInteger.valueOf(2) ));
    }


    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;

        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        return thread1.getResult().add(thread2.getResult());
    }




    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           result = base.pow(power.intValue());
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
