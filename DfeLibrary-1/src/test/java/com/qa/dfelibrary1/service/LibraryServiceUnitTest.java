package com.qa.dfelibrary1.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.dfelibrary1.data.Library;
import com.qa.dfelibrary1.repo.LibraryRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LibraryServiceUnitTest {

	@Autowired
	private LibraryServiceDB service;

	@MockBean
	private LibraryRepo repo;

	@Test
	void testGetByName() {
		final String name = "Gone Girl";
		final List<Library> libraries = List.of(new Library(1, name, "Gillian Flynn", "Thriller"));

		Mockito.when(this.repo.findByName(name)).thenReturn(libraries);

		assertThat(this.service.getLibraryByName(name)).isEqualTo(libraries);

		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}

	@Test
	void testGetById() {
		final Integer Id = 1;
		final Library library = new Library(Id, "Gone Girl", "Gillian Flynn", "Thriller");

		Mockito.when(this.repo.findById(Id)).thenReturn(Optional.of(library));

		assertThat(this.service.getLibraryByIndex(Id)).isEqualTo(library);

		Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
	}

	@Test
	void testGetAllMLibraries() {
		final List<Library> libraries = List.of(new Library(1, "Gone Girl", "Gillian Flynn", "Thriller"),
				new Library(2, "The Reckoning", "John Grisham", "Legal Thriller"));

		Mockito.when(this.repo.findAll()).thenReturn(libraries);

		assertThat(this.service.getAllLibraries()).isEqualTo(libraries);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testUpdate() { 
		final Integer id = 1;
		Library library = new Library(id, "Gone Girl", "Gillian Flynn", "Thriller");
		Optional<Library> optionalLibrary = Optional.of(library);

		Library newLibrary = new Library(id, "The Reckoning", "John Grisham", "Legal Thriller");

		Mockito.when(this.repo.findById(id)).thenReturn(optionalLibrary);
		Mockito.when(this.repo.save(newLibrary)).thenReturn(newLibrary);

		assertThat(this.service.updateLibrary(newLibrary, library.getId())).isEqualTo(newLibrary);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(newLibrary);
	}

	@Test
	void testCreate() {
		Library newLibrary = new Library(null, "Gone Girl", "Gillian Flynn", "Thriller");
		Library savedLibrary = new Library(1, "Gone Girl", "Gillian Flynn", "Thriller");

		Mockito.when(this.repo.save(newLibrary)).thenReturn(savedLibrary);

		assertThat(this.service.createLibrary(newLibrary)).isEqualTo(savedLibrary);

		Mockito.verify(this.repo, Mockito.times(1)).save(newLibrary);
	}

	@Test
	void testDelete() {
		final Integer id = 1;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.deleteLibrary(id)).isEqualTo(true);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}
