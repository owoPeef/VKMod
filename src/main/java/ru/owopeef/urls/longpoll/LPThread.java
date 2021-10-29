package ru.owopeef.urls.longpoll;

@SuppressWarnings("BusyWait")
public class LPThread extends Thread {
    public static String server;
    public static String key;
    public static int ts;
    @Override
    public void run() {
        while (true) {
            TestLP.LongPoll();
            try {
                Thread.sleep(1002);
            }
            catch (InterruptedException ignored) {}
        }
    }
}
