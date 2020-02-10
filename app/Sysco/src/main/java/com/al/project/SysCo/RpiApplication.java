package com.al.project.SysCo;


import com.al.project.SysCo.RPi.Rpi;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
//@EnableAutoConfiguration

public class RpiApplication {

	public static void main(String[] args) {

		ArrayList<Rpi> rpiList = new ArrayList<>();

		for (int id=0; id<50; id++){
			rpiList.add(new Rpi(id));
		}
	}
}
