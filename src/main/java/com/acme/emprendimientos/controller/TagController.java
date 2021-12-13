package com.acme.emprendimientos.controller;

import com.acme.emprendimientos.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tag")
public class TagController {
    private final TagRepository tagRepository;

    @Autowired
    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    //GET All Tags
    @GetMapping
    public ResponseEntity<?> getTags() {
        return new ResponseEntity<>(tagRepository.findAll(), HttpStatus.OK);
    }
}
