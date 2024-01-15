package com.techmahindra.libraryservice.service;

import com.techmahindra.libraryservice.model.Library;
import com.techmahindra.libraryservice.repository.LibraryRepository;
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
