package frclib.time;

public class Time {

	private static boolean done = true;
	private static long endtime = 0;

	public static void delayMiliseconds(long miliseconds) {
		long endtime = System.currentTimeMillis() + miliseconds;
		while (System.currentTimeMillis() < endtime) {
		}
	}

	public static void delaySeconds(long seconds) {
		long endtime = System.currentTimeMillis() + (seconds * 1000);
		while (System.currentTimeMillis() < endtime) {
		}
	}

	public static void runnableDelay(long milis, Runnable r) {
		endtime = System.currentTimeMillis() + (milis * 1000);
		done = false;
	}

	public static boolean runnableDelay() {
		if (System.currentTimeMillis() > endtime) {
			done = true;
		}
		return done;
	}
}
