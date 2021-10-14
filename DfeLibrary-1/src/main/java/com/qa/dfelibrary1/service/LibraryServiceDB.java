package com.qa.dfelibrary1.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.dfelibrary1.data.Library;
import com.qa.dfelibrary1.repo.LibraryRepo;

@Service
@Primary
public class LibraryServiceDB implements LibraryService {
	
	private LibraryRepo repo;
	
	
	
	public LibraryServiceDB(LibraryRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<Library> getLibraryByName(String name) {
		return this.repo.findByName(name);
	}

	@Override
	public Library getLibraryByIndex(Integer id) {
		return this.repo.findById(id).get();
	}

	@Override
	public List<Library> getAllLibraries() {
		return this.repo.findAll();
	}

	@Override
	public Library createLibrary(Library library) {
		return this.repo.save(library);
	}

	@Override
	public Library updateLibrary(Library library, Integer id) {
		
		Library toUpdate = this.repo.findById(id).get();

		toUpdate.setGenre(library.getGenre());
		toUpdate.setAuthor(library.getAuthor());
		toUpdate.setName(library.getName());

		return this.repo.save(toUpdate);
	}

	@Override
	public void deleteLibrary(Integer id) {
		this.repo.deleteById(id);
	}

}