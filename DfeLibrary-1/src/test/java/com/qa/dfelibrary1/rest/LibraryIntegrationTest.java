package com.qa.dfelibrary1.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.dfelibrary1.data.Library;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 

public class LibraryIntegrationTest {

	@Autowired 
	private MockMvc mvc;

		@Autowired
		private ObjectMapper mapper;

		@Test
		void testCreate() throws Exception {
			final Library testLibrary = new Library(null, "Gone Girl", "Gillian Flynn", "Thriller");
			String testLibraryAsJSON = this.mapper.writeValueAsString(testLibrary);

			final Library savedLibrary = new Library(1, "Gone Girl", "Gillian Flynn", "Red");
			String savedLibraryAsJSON = this.mapper.writeValueAsString(savedLibrary);

			
			RequestBuilder request = post("/createLibrary").contentType(MediaType.APPLICATION_JSON)
					.content(testLibraryAsJSON);

			ResultMatcher checkStatus = status().isCreated();
			ResultMatcher checkContent = content().json(savedLibraryAsJSON);

			this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
		}

		void testCreateAbrdiged() throws Exception {
			final String testLibraryAsJSON = this.mapper
					.writeValueAsString(new Library(null, "Jack", "Kangaroo", "Red"));
			final String savedLibraryAsJSON = this.mapper.writeValueAsString(new Library(1, "Jack", "Kangaroo", "Red"));

			this.mvc.perform(post("/createLibrary").contentType(MediaType.APPLICATION_JSON).content(testLibraryAsJSON))
					.andExpect(status().isCreated()).andExpect(content().json(savedLibraryAsJSON));

		}
	
}
