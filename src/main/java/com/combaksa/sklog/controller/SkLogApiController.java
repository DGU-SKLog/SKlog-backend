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
    public ResponseEntity<UserResponseDto> createTable(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = skLogApiService.createTable(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/list")
    public ResponseEntity<UserResponseDto> createList(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = skLogApiService.createList(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/summary")
    public ResponseEntity<UserResponseDto> createSummary(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = skLogApiService.createSummary(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/expansion")
    public ResponseEntity<UserResponseDto> createExpansion(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = skLogApiService.createExpansion(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/edit")
    public ResponseEntity<UserResponseDto> createEdit(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = skLogApiService.createExpansion(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @PostMapping("/api/request")
    public ResponseEntity<UserResponseDto> createUserResponse(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = skLogApiService.createUserResponse(requestDto);

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
