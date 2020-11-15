package com.manager.controller;

import com.manager.model.Note;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestSecurityController {

//    @GetMapping
//    public String login() {
//
//        return "authenticated succesfully";
//    }

//    @GetMapping
//    public Note getNote() {
//        return new Note(1, "jakaś ciekawa notatka");
//    }
}
