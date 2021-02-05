package com.manager.controller;

import com.manager.model.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping({ "/api" })
public class LoginController {

    private List<String> tasks = createList();

    @GetMapping(produces = "application/json")
    public List<String> firstPage() {
        return tasks;
    }


    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public Message validateLogin() {
        return new Message("User successfully authenticated");
    }

//    @DeleteMapping(path = { "/{id}" })
//    public String delete(@PathVariable("id") int id) {
//        String deletedEmp = null;
//        for (String tsk : tasks) {
//            if (tsk.equals(id)) {
//                tasks.remove(tsk);
//                deletedEmp = tsk;
//                break;
//            }
//        }
//        return deletedEmp;
//    }

//    @PostMapping
//    public Task create(@RequestBody Task tsk) {
//        tasks.add(tsk);
//        return tsk;
//    }

    private static List<String> createList() {
        List<String> tasks = new ArrayList<>();
        String tsk1 = "task1";
        String tsk2 = "task2";
        String tsk3 = "task3";

        tasks.add(tsk1);
        tasks.add(tsk2);
        tasks.add(tsk3);
        return tasks;
    }
}
