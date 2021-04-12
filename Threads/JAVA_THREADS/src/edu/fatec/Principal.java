package edu.fatec;

public class Principal {
	
	public static void main(String[] args) {
		Fatorial job = new Fatorial();
		
		Thread th1 = new Thread(job, "Num1");
		th1.start();
		Thread th2 = new Thread(job, "Num2");
		th2.start();
		Thread th3 = new Thread(job, "Num3");
		th3.start();
		Thread th4 = new Thread(job, "Num4");
		th4.start();
		Thread th5 = new Thread(job, "Num5");
		th5.start();
	}
}
