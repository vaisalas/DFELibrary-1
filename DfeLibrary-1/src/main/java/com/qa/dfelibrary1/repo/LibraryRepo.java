package com.qa.dfelibrary1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.dfelibrary1.data.Library;

@Repository

public interface LibraryRepo extends JpaRepository<Library, Integer> {

	List<Library> findByName(String name);

}
