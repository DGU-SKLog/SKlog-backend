package com.combaksa.sklog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "request", nullable = false)
    private String request;

    @Column(name = "userContent", nullable = false)
    private String userContent;

    @Column(name = "aiContent", nullable = true)
    private String aiContent;
}
