package com.qa.dfelibrary1;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public Library getLibrarylByIndex(@PathVariable Integer id) {

		return this.librarys.get(id);
	}

	@GetMapping("/getAllLibrarys")
	public List<Library> getAllLibrarys() {

		return this.librarys;
	}

	@PostMapping("/createLibrary")
	public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
		System.out.println("CREATED Library: " + library);
		this.librarys.add(library);
		Library responseBody = this.librarys.get(this.librarys.size() - 1);
		ResponseEntity<Library> response = new ResponseEntity<Library>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateLibrary/{id}")
	public ResponseEntity<Library> updateLibrary(@RequestBody Library library, @PathVariable Integer id) {
		System.out.println("UPDATED Library: " + library);
		System.out.println("ID: " + id);
		Library responseBody = this.librarys.set(id, library);
		return new ResponseEntity<Library>(responseBody, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/removeLibrary/{id}") 
	public ResponseEntity<?> deleteLibrary(@PathVariable Integer id) {
		Library toDelete = this.librarys.get(id);
		this.librarys.remove(toDelete);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
