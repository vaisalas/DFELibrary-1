package com.qa.dfelibrary1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.dfelibrary1.rest.LibraryController;
import com.qa.dfelibrary1.service.LibraryService;
import com.qa.dfelibrary1.service.LibraryServiceList;

@SpringBootApplication
public class DfeLibrary1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DfeLibrary1Application.class, args);

		LibraryController controllerBean = context.getBean(LibraryController.class);

		System.out.println(controllerBean);

		LibraryController myController = new LibraryController(new LibraryServiceList());

		System.out.println(new LibraryServiceList() instanceof LibraryService);
	}
}
