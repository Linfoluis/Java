package edu.fatec;

import javax.swing.JOptionPane;

public class Fatorial implements Runnable {

	@Override
	public void run() {
		double fat = 0; 
		int num = 0;
		boolean primo = true;
		num = Integer.parseInt(JOptionPane.showInputDialog("Entre com um n�mero"));
		
		for (int i = 2; i < num; i++) {
			if (num% i == 0) {
				primo = false;
			}
		}
		if (primo == false) {
			JOptionPane.showMessageDialog(null, "N�O � N�MERO PRIMO");
		}
		
		if (primo == true) {
			fat = num;
			for (int j = num-1; j > 0; j--) {
				fat = fat * j;				
			}
			System.out.println("O fatorial do n�mero primo " + num + " �: " + fat);
		}
		
	}
		
}
//			try {
//				//Thread.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


