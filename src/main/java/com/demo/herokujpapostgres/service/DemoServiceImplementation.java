package com.demo.herokujpapostgres.service;

import com.demo.herokujpapostgres.model.DemoModel;
import com.demo.herokujpapostgres.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImplementation implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public List<DemoModel> getAll() {
        return demoRepository.findAll();
    }

    @Override
    public void add(DemoModel demoModel) {
        demoRepository.save(demoModel);
    }
}
