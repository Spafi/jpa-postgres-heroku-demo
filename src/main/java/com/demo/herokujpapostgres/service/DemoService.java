package com.demo.herokujpapostgres.service;

import com.demo.herokujpapostgres.model.DemoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DemoService {
    List<DemoModel> getAll();
    void add(DemoModel demoModel);
}
