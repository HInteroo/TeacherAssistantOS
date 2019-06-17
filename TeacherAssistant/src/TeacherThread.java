import java.util.concurrent.Semaphore;
public class TeacherThread implements Runnable
	{
		boolean Woken;
	    public Semaphore availableChairs;
	    public Semaphore available;
	    private Thread CurrentThread;
	    
	    public TeacherThread(boolean w, Semaphore c, Semaphore a)
	    {
	        Woken = w;
	        availableChairs = c;
	        available = a;
	        CurrentThread = Thread.currentThread();
	    }
	    
	    public synchronized void Threadwait() {
			while(!this.Woken)
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    @Override
	    public void run()
	    {
	        while (true)
	        {
	        	if(Woken) {
		        	if(availableChairs.availablePermits() == 3) {
		        		System.out.println("There are no students waiting. The Teacher is going to take a nap.");
		        		Woken = false;
		        	}
		        	if(availableChairs.availablePermits()!=3) {
		                System.out.println("The teacher has woken up by a student.");
		        	}
	        	}
	        }
	    }
}
