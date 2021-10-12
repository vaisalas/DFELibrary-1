package com.qa.dfelibrary1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.dfelibrary1.data.Library;

//@Primary
@Service

public class LibraryServiceList implements LibraryService {
	
	private List<Library> libraries = new ArrayList<>();

	@Override
	public List<Library> getLibraryByName(String name) {
		
		return this.libraries.stream().filter(library -> name.equalsIgnoreCase(library.getName()))
				.collect(Collectors.toList());
	}
	
	
	@Override
	public Library getLibraryByIndex(Integer id) {

		return this.libraries.get(id);
	}

	@Override
	public List<Library> getAllLibraries() {

		return this.libraries;
	}

	@Override
	public Library createLibrary(Library library) {
		System.out.println("CREATED Library: " + library);
		this.libraries.add(library);
		return this.libraries.get(this.libraries.size() - 1);
	}

	@Override
	public Library updateLibrary(Library library, Integer id) {
		System.out.println("UPDATED Library: " + library);
		System.out.println("ID: " + id);
		return this.libraries.set(id, library); 
	}

	@Override
	public void deleteLibrary(Integer id) {
		Library toDelete = this.libraries.get(id);
		this.libraries.remove(toDelete);
	}


}
