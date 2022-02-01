package com.colpatria.bootcamp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table( name = "message")
public class MessageJpaEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_seq"
    )
    @SequenceGenerator(
            name = "message_seq",
            allocationSize = 5
    )
    private Long id;

    @NotBlank
    private String message;
}
