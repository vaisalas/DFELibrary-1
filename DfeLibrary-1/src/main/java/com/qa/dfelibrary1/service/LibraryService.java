package com.qa.dfelibrary1.service;

import java.util.List;

import com.qa.dfelibrary1.data.Library;

public interface LibraryService {
	
	public List<Library> getLibraryByName (String name);

	public Library getLibraryByIndex(Integer id);

	public List<Library> getAllLibraries();

	public Library createLibrary(Library library);

	public Library updateLibrary(Library library, Integer id);

	public boolean deleteLibrary(Integer id);
	
}
