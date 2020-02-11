package com.al.project.SysCo;


import com.al.project.SysCo.RPi.Rpi;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

//@SpringBootApplication
//@EnableAutoConfiguration

public class RpiApplication {

	public static void main(String[] args) {

		final int numberRoom = 2;
		ArrayList<Rpi> rpiList = new ArrayList<>();

		for (int id=0; id<numberRoom; id++){
			rpiList.add(new Rpi(id));
		}

		Scanner sc = new Scanner(System.in);

		//sc.nextLine();
		//String str = sc.nextLine();
		System.out.println("FIN ! ");
	}
}
