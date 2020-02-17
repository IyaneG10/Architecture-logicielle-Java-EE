package com.al.project.SysCo;


import com.al.project.SysCo.RPi.Rpi;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.util.*;

//@SpringBootApplication
//@EnableAutoConfiguration

public class RpiApplication  {

	public static void main(String[] args) {

		final int numberRoom = 1;

		long id= 0;
		ArrayList<Rpi> rpiList = new ArrayList<>();


		for (int i = 0; i<numberRoom; i++){
			rpiList.add(new Rpi (id));
			rpiList.get(i).start();
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
