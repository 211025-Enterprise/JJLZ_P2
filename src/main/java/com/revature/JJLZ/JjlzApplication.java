package com.revature.JJLZ;

import com.revature.JJLZ.model.User;
import com.revature.JJLZ.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class JjlzApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JjlzApplication.class, args);
		User me = new User();
		me.setUsername("me");
		me.setPassword("me");
		Scanner sc = new Scanner(System.in);
		if (sc.nextLine() == "1"){
			UserService userService = null;
			System.out.println(userService.totalBalance(me));
		}
	}

}
