package com.combaksa.sklog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.combaksa.sklog.dto.*;
import com.combaksa.sklog.service.SkLogApiService;

@RestController
@RequiredArgsConstructor
public class SkLogApiController {

    private final SkLogApiService skLogApiService;

    @PostMapping("/api/table")
    public ResponseEntity<ContentResponseDto> createTable(@RequestBody ContentRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createTable(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/list")
    public ResponseEntity<ContentResponseDto> createList(@RequestBody ContentRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createList(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/summary")
    public ResponseEntity<ContentResponseDto> createSummary(@RequestBody ContentRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createSummary(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/expansion")
    public ResponseEntity<ContentResponseDto> createExpansion(@RequestBody ContentRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createExpansion(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/edit")
    public ResponseEntity<ContentResponseDto> createEdit(@RequestBody ContentRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createExpansion(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/request")
    public ResponseEntity<ContentResponseDto> createUserResponse(@RequestBody UserRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createUserResponse(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/question")
    public ResponseEntity<ContentResponseDto> createAnswer(@RequestBody UserRequestDto requestDto){
        ContentResponseDto responseDto = skLogApiService.createAnswer(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }



}
