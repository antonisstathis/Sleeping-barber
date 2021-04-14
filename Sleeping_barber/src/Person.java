import java.awt.Button;
import java.util.Random;
import java.util.concurrent.Semaphore;

import gui.BarberGui;

public class Person implements Runnable {
	
	private Semaphore semWaitRoom;
	private int positions=4;
	private Button person;
	private Barber barber;
	private String gender;
	private int id;
	private BarberGui barbergui;
	private Random rand;
	private long period;
	
	
	public Person(Semaphore semWaitRoom,BarberGui barbergui,Barber barber,String name,String gender,int p,int s) {
		
		this.semWaitRoom=semWaitRoom;
		
		this.barbergui=barbergui;
		this.barber=barber;
		person = new Button(name);
		this.gender=gender;
		this.id=p;
		rand = new Random();
		period=0;
		barbergui.add(person);
		person.setLocation(barbergui.pos[p][s]);
		person.setSize(20, 20);
	}

	@Override
	public void run() {
			
		while (true) {
			enjoy();
			go2BarberShop();
			
			acquireSemWaitRoom();
			enterWaitingRoom(); // Critical region, no more than 4 in waiting room
			
			barber.cutHairs(this); // Critical region, no more than 1
		}
			
		
	}

	public void enjoy() {
		
		period=(long) (Math.random() * (2000 - 600 + 1) + 600);
		this.person.setLocation(barbergui.pos[id][0]);
		sleep(period);
	}
	
	public void go2BarberShop() {
		
		period=(long) (Math.random() * (2000 - 600 + 1) + 600);
		this.person.setLocation(barbergui.pos[id][1]);
		sleep(period);
	}
	
	public void enterWaitingRoom() {
		
		period=(long) (Math.random() * (2000 - 600 + 1) + 600);
		this.person.setLocation(barbergui.pos[id][2]);
		sleep(period);
	}
	
	public void enterServiceRoom() {
		
		semWaitRoom.release();
		this.person.setLocation(barbergui.pos[id][3]);
	}
	
	public void getHairCut() {
		
		period=barber.sleep();
		sleep(period);
	}
		
	
	public void sleep(long period) {
		
		try {
			Thread.sleep(period);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public String getGender() {
		
		return gender;
	}
	
	public void acquireSemWaitRoom() {
		
		try {
			semWaitRoom.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
