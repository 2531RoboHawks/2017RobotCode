package frclib.time;

public class Time {

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
}
