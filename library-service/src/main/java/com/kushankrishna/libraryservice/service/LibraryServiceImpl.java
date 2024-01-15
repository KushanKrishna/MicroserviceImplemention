package com.kushankrishna.libraryservice.service;

import com.kushankrishna.libraryservice.repository.LibraryRepository;
import com.kushankrishna.libraryservice.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    @Override
    public Library saveLibrary(Library library) {
        return this.libraryRepository.save(library);
    }
}
