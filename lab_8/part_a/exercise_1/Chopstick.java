public class Chopstick {
	private int ID;
	private boolean free;

	Chopstick(int ID) {
		  this.ID = ID;
			this.free = true;
	}

	synchronized void take() {
		if (!this.free) {
			try {
					wait();
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		this.free = false;
	}

	synchronized void release() {
		this.free = true;
		notify();
	}

	public int getID() {
	    return(ID);
	}
}
