package com.colpatria.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table( name = "user")
public class UserJpaEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @SequenceGenerator(
            name = "user_seq",
            allocationSize = 5
    )
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String status;
}
