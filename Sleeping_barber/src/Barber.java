import java.util.concurrent.Semaphore;

public class Barber {

	private long period;
	private Person person;
	private Semaphore semaphore;

	public Barber (Semaphore semaphore) {
		
		this.semaphore=semaphore;
	}
	
	public synchronized void cutHairs(Person person) {
		
		//acquireSemaphore();
		this.person=person;
		person.enterServiceRoom();
		person.getHairCut();
		//semaphore.release();
	}
	
	public long sleep() {
			
		if(person.getGender()=="female")
			period = (long) 2100;
		
		if(person.getGender()=="male")
			period = (long) 1000;
			
			return period;
	}
	
	public void acquireSemaphore() {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
