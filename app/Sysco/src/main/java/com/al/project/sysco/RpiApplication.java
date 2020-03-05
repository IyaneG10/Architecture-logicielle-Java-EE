package com.al.project.sysco;


import com.al.project.sysco.Service.Rpi;

import java.util.ArrayList;
import java.util.Scanner;

public class RpiApplication  {

	public static void main(String[] args) {

		int numberRoom =50;

		try {

			System.out.println(" [i] START !\n [i] To stop, type 'exit'\n\n");

			int firstArg;
			if (args.length > 0) {
				try {
					numberRoom = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					System.err.println(" [-] Argument" + args[0] + " must be an integer.\n [i] Using default value : 50 RPi");
					numberRoom = 50;
				}
			}


			ArrayList<Rpi> rpiList = new ArrayList<>();


			for (int id = 0; id<numberRoom; id++) {
				rpiList.add(new Rpi(id));
				Thread.sleep(250);
				rpiList.get(id).start();
			}

			Scanner sc = new Scanner(System.in);

			String txt = sc.nextLine();

			while (! "exit".equalsIgnoreCase(txt)){
				System.out.println(" [x] Wrong command. Please, type 'exit'");
				txt = sc.nextLine();
			}

			for (int i=0; i<numberRoom; i++){
				rpiList.get(i).stopTasks();
			}

			System.out.println(" [i] END ! ");
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}