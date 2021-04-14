import java.awt.Button;
import java.awt.Point;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import gui.BarberGui;

public class App {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		BarberGui barbergui = new BarberGui();
		String[] gender = {"male","female"};
		Semaphore semaphore = new Semaphore(1);
		Semaphore semWaitRoom = new Semaphore(4);
		ArrayBlockingQueue array = new ArrayBlockingQueue<Person>(4);
		
		Person[] array1 = new Person[4];
		Person[] person = new Person[10];
		Barber barber = new Barber(semaphore);
		
		for (int i=0;i<10;i++) {
			String s = String.valueOf(i);
			person[i] = new Person(semWaitRoom,barbergui,barber,s,gender[i%2],i,0);
			executorService.execute(person[i]);
		}
		
		
	}

}
