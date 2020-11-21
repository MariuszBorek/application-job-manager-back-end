package com.manager.controller;

import com.manager.model.Note;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestSecurityController {

    @GetMapping
    public String login() {

        return "authenticated succesfully";
    }

    @GetMapping("/note")
    public Note getNote() {
        return new Note("jakaś ciekawa notatka");
    }
}
