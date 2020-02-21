package com.al.project.SysCo;


import com.al.project.SysCo.RPi.Rpi;

import java.util.ArrayList;
import java.util.Scanner;

//@SpringBootApplication
//@EnableAutoConfiguration

public class RpiApplication  {

	public static void main(String[] args) {

		final int numberRoom =50;

		ArrayList<Rpi> rpiList = new ArrayList<>();


		for (int id = 0; id<numberRoom; id++){
			rpiList.add(new Rpi (id));
			rpiList.get(id).start();
		}


		Scanner sc = new Scanner(System.in);

		System.out.println(" START !\n To stop, type 'exit'");
		String txt = sc.nextLine();

		while (! "exit".equalsIgnoreCase(txt)){
			System.out.println(" Wrong command. Type 'exit'");
			txt = sc.nextLine();
		}

		for (int i=0; i<numberRoom; i++){
			rpiList.get(i).stopTasks();
		}
		System.out.println(" FIN ! ");
	}
}
