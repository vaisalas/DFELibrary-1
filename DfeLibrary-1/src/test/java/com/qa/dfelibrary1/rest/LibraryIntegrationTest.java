package com.qa.dfelibrary1.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dfelibrary1.data.Library;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 
@Sql(scripts = { "classpath:library-schema.sql",
		"classpath:library-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class LibraryIntegrationTest {

	@Autowired 
	private MockMvc mvc;

		@Autowired
		private ObjectMapper mapper;

		@Test
		void testCreate() throws Exception {
			final Library testLibrary = new Library(1, "Gone Girl", "Gillian Flynn", "Thriller");
			String testLibraryAsJSON = this.mapper.writeValueAsString(testLibrary);

			final Library savedLibrary = new Library(1, "Gone Girl", "Gillian Flynn", "Thriller");
			String savedLibraryAsJSON = this.mapper.writeValueAsString(savedLibrary);

			
			RequestBuilder request = post("/createLibrary").contentType(MediaType.APPLICATION_JSON)
					.content(testLibraryAsJSON);

			ResultMatcher checkStatus = status().isCreated();
			ResultMatcher checkContent = content().json(savedLibraryAsJSON);

			this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
		}


		@Test
		void testGetAll() throws Exception {
			String savedLibraryAsJSON = this.mapper
					.writeValueAsString(List.of(new Library(1, "Gone Girl", "Gillian Flynn", "Thriller")));

			RequestBuilder request = get("/getAllLibraries");

			ResultMatcher checkStatus = status().isOk();
			ResultMatcher checkContent = content().json(savedLibraryAsJSON);

			this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
		}

		@Test
		void testGetById() throws Exception {
			final Library savedLibrary = new Library(1, "Gone Girl", "Gillian Flynn", "Thriller");
			String savedLibraryAsJSON = this.mapper.writeValueAsString(savedLibrary);

			RequestBuilder request = get("/getLibrary/" + savedLibrary.getId());

			ResultMatcher checkStatus = status().isOk();
			ResultMatcher checkContent = content().json(savedLibraryAsJSON);

			this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
		}

		@Test
		void testUpdate() throws Exception {
			final Library testLibrary = new Library(1, "Harry Potter and the Deathly Hallows", "J. K. Rowling", "Fantasy");	
			final String testLibraryAsJSON = this.mapper.writeValueAsString(testLibrary);

			RequestBuilder request = put("/updateLibrary/1").contentType(MediaType.APPLICATION_JSON)
					.content(testLibraryAsJSON);

			ResultMatcher checkStatus = status().isAccepted();
			ResultMatcher checkContent = content().json(testLibraryAsJSON);

			this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
		}

		@Test
		void testDelete() throws Exception {
			this.mvc.perform(delete("/removeLibrary/1")).andExpect(status().isNoContent());
		}
		
		void testCreateAbrdiged() throws Exception {
			final String testLibraryAsJSON = this.mapper
					.writeValueAsString(new Library(null, "Gone Girl", "Gillian Flynn", "Thriller"));
			final String savedLibraryAsJSON = this.mapper.writeValueAsString(new Library(1, "Gone Girl", "Gillian Flynn", "Thriller"));

			this.mvc.perform(post("/createLibrary").contentType(MediaType.APPLICATION_JSON).content(testLibraryAsJSON))
					.andExpect(status().isCreated()).andExpect(content().json(savedLibraryAsJSON));

		}
	
}
