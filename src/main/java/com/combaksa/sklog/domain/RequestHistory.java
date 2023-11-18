package com.combaksa.sklog.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @Builder
    public RequestHistory(String request, String userContent, String aiContent){
        this.request = request;
        this.userContent = userContent;
        this.aiContent = aiContent;
    }
}
