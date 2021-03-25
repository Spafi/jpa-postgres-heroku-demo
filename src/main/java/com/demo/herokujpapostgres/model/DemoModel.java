package com.demo.herokujpapostgres.model;

import lombok.*;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demo")
public @Data class DemoModel {

    @Id
    @Type(type = "pg-uuid")  // To convert Java UUID to PostgreSQL UUID
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "column_name")  // Use this for column name in pgAdmin
    private String name;  // Use this for HTTP Requests
}
