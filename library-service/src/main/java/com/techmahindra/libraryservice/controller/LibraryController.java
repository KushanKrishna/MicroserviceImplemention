package com.techmahindra.libraryservice.controller;

import com.techmahindra.libraryservice.dto.AddBookResponseDto;
import com.techmahindra.libraryservice.dto.AddLibraryResponseDto;
import com.techmahindra.libraryservice.model.Library;
import com.techmahindra.libraryservice.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @PostMapping("/addLibrary")
    public ResponseEntity<?> addLibrary(@RequestBody Library library) {
        library.getAvailableBookList().stream().forEach(book -> book.setLibrary(library));
        library.getIssuedBookList().stream().forEach(book -> book.setLibrary(library));
        library.setAvailableBooksCount((long) (library.getAvailableBookList().size()));
        library.setIssuedBooksCount((long) (library.getIssuedBookList().size()));
        if (Objects.nonNull(this.libraryService.saveLibrary(library))) {

            AddLibraryResponseDto addLibraryResponseDto = new AddLibraryResponseDto();
            addLibraryResponseDto.setStatus("success");
            addLibraryResponseDto.setTimestamp(new Timestamp(new Date().getTime()));
            addLibraryResponseDto.setMessage("Library added Successfully");
            addLibraryResponseDto.setHttpStatus(HttpStatus.OK.value());
            return new ResponseEntity<>(addLibraryResponseDto, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
