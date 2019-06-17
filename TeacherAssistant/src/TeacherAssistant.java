import java.util.Random;
import java.util.concurrent.Semaphore;

public class TeacherAssistant {
	
    boolean WakeTeacher;
    Thread studentThreadl;
	int numOfStudents;
	Semaphore chairs;
	Semaphore available;
    Random randInt = new Random(); 
    Thread studentThread;
    Thread teacherThread;
    
	public TeacherAssistant(boolean woken, int Students, int C, int A) {
		WakeTeacher = woken;
		numOfStudents = Students;
		chairs = new Semaphore (C);
		available = new Semaphore (A);
		studentThread = null;
	}

    public void createTeacherStudentThread() {
        for (int i = 0; i < 5; i++)
        {
            studentThread = new Thread(new StudentThread(randInt.nextInt(30), this.chairs, this.available, i+1));
            studentThread.start();
        }			
        teacherThread = new Thread(new TeacherThread(this.WakeTeacher, this.chairs, this.available));	//Create and start TA Thread.
        teacherThread.start();	//Teacher thread started
    }
    
    public static void main(String[] args) 
    {
    	TeacherAssistant Teacher = new TeacherAssistant(true, 10, 3, 1);
    	Teacher.createTeacherStudentThread();
    }
}