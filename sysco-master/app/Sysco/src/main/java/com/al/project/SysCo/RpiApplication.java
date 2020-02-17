package com.al.project.SysCo;


import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.User;
import com.al.project.SysCo.RPi.Rpi;
import com.al.project.SysCo.Repository.DataRepository;
import com.al.project.SysCo.Repository.RoleRepository;
import com.al.project.SysCo.Repository.UserRepository;
import com.al.project.SysCo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Console;
import java.util.*;

//@SpringBootApplication
//@EnableAutoConfiguration

public class RpiApplication  {

	public static void main(String[] args) {



		final int numberRoom = 50;

		ArrayList<Rpi> rpiList = new ArrayList<>();

		for (int id=0; id<numberRoom; id++){
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

		for (int id=0; id<numberRoom; id++){
			rpiList.get(id).stopTasks();
		}
		System.out.println(" FIN ! ");
	}
}
