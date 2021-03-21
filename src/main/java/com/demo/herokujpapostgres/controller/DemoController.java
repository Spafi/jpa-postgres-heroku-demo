package com.demo.herokujpapostgres.controller;

import com.demo.herokujpapostgres.model.DemoModel;
import com.demo.herokujpapostgres.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/demo")
public class DemoController {
    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    public ResponseEntity<List<DemoModel>> get() {
        return new ResponseEntity<>(demoService.getAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public void addA(@RequestBody DemoModel demoModel) {
        demoService.add(demoModel);
    }

}