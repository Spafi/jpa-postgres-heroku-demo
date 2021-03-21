package com.demo.herokujpapostgres.repository;

import com.demo.herokujpapostgres.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, UUID> {
}