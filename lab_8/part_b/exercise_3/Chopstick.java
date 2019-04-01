import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Chopstick {
	private int ID;
	private boolean free;
	private Semaphore semaphore = new Semaphore(1);

	final long maxWait = 420;

	Chopstick(int ID) {
		  this.ID = ID;
			this.free = true;
	}

	synchronized boolean take() {
		if (!this.free) {
			try {
				if (!this.semaphore.tryAcquire(maxWait, TimeUnit.MILLISECONDS)) {
					return false;
				}
			} catch(InterruptedException e) {
				return false;
			}
		}
		this.free = false;
		return true;
	}

	synchronized void release() {
		this.free = true;
		notify();
	}

	public int getID() {
	    return(ID);
	}
}
