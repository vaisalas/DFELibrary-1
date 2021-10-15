package com.qa.dfelibrary1.rest;

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

import com.qa.dfelibrary1.data.Library;
import com.qa.dfelibrary1.service.LibraryService;

@CrossOrigin
@RestController

public class LibraryController {


	private LibraryService service;


	public LibraryController(LibraryService service) {
		super();
		this.service = service;
	}

	
	@GetMapping("/getLibrary/{id}") 
	public Library getLibraryByIndex(@PathVariable Integer id) {

		return this.service.getLibraryByIndex(id);
	}

	@GetMapping("/getAllLibraries")
	public List<Library> getAllLibraries() {

		return this.service.getAllLibraries();
	}

	@PostMapping("/createLibrary")
	public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
		Library responseBody = this.service.createLibrary(library);
		ResponseEntity<Library> response = new ResponseEntity<Library>(responseBody, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/updateLibrary/{id}")
	public ResponseEntity<Library> updateLibrary(@RequestBody Library library, @PathVariable Integer id) {
		Library responseBody = this.service.updateLibrary(library, id);
		return new ResponseEntity<Library>(responseBody, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/removeLibrary/{id}") 
	public ResponseEntity<?> deleteLibrary(@PathVariable Integer id) {
		boolean deleted = this.service.deleteLibrary(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	
	
	}
}
