package com.qa.dfelibrary1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin

public class LibraryController {

	private List<Library> librarys = new ArrayList<>();



	@GetMapping("/getLibrary/{id}")
	public Library getLibraryByIndex(@PathVariable Integer id) {
		return this.librarys.get(id);
	}

	@GetMapping("/getAllLibrary")
	public List<Library> getAllLibrary() {
		return this.librarys;
	}

	@PostMapping("/createLibrary")
	public Library createLibrary(@RequestBody Library library) {
		System.out.println("CREATED Library: " + library);
		this.librarys.add(library);
		return this.librarys.get(this.librarys.size() - 1);
	}

	@PutMapping("/updateLibrary/{id}")
	public Library updateLibrary(@RequestBody Library library, @PathVariable Integer id) {
		System.out.println("UPDATED Library: " + library);
		System.out.println("ID: " + id);
		return this.librarys.set(id, library);
	}

	@DeleteMapping("/removeLibrary/{id}")
	public String deleteLibrary(@PathVariable Integer id) {
		Library toDelete = this.librarys.get(id);
		this.librarys.remove(toDelete);
		return "Deleted: " + toDelete;
	}
	
}
