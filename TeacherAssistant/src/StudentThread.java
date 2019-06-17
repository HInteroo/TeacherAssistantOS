import java.util.concurrent.Semaphore;

public class StudentThread implements Runnable
	{   
	    private Thread CurrentThread;
	    private int ID;
	    private int programmingTime;
	    private Semaphore availableChairs;
	    private Semaphore available;
	    private TeacherAssistant threads;

	    public StudentThread(int T, Semaphore c, Semaphore a, int num)
	    {
	        programmingTime = T;    
	        availableChairs = c;
	        available = a;
	        ID = num;
	        CurrentThread = Thread.currentThread();
	    }

	    @SuppressWarnings("static-access")
		@Override
	    public void run()
	    {
	        while(true)
	        {
               System.out.println("ID of student: " + ID + " \t\tProgramming Time: " + programmingTime + " seconds.");
               try {
				CurrentThread.sleep(programmingTime * 1200);
               } catch (InterruptedException e) {
            	   e.printStackTrace();
               }
               if (available.tryAcquire()) {
   	        		System.out.println("Student "+ID+" has woken up the teacher.");
                    try {
                        System.out.println("Student "+ID+" began working with the teacher");
						CurrentThread.sleep(programmingTime * 1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                    System.out.println("Student " + ID + " has stopped working with the TA.");
                    available.release();
                }
               else
               {
                   System.out.println("Student " + ID + " couldn't work with the Teacher.");
                   if (availableChairs.tryAcquire())
                   {
                       System.out.println("Student " + ID + " is seating on chair " +(Math.abs(availableChairs.availablePermits()-3))+".");
                       try {
	                       available.acquire();
	                       System.out.println("Student "+ID+" began working with the teacher.");
	                       availableChairs.release();
	                       System.out.println("Available seats: "+availableChairs.availablePermits());
	                       CurrentThread.sleep(programmingTime * 1000);
	                       System.out.println("Student "+ID+" finished working with the teacher.");
	                       available.release();
	   					} catch (InterruptedException e) {
							e.printStackTrace();
						}
                   }
                   else
                   {
                       System.out.println("No available seats for student "+ID);
                   }
               }
	        }
	    }
	


}
