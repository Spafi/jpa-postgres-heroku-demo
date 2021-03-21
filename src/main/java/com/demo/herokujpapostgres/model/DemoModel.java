package com.demo.herokujpapostgres.model;

import lombok.*;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "demo")
public class DemoModel {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    @Column(name = "column_name")  // Use this for column name in pgAdmin
    private String name;  // Use this for HTTP Requests
}
