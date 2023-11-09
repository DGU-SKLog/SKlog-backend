package com.combaksa.sklog.service;

import com.combaksa.sklog.dto.*;

import org.springframework.stereotype.Service;

@Service
public class SkLogApiService {

    public ContentResponseDto createTable(ContentRequestDto requestDto){
        //ai서버 파이썬에 보내(requsetDto.getContent())

        String content = requestDto.getContent();
        content += "이렇게 표로 만듬.";

        return new ContentResponseDto(content);
    }

    public ContentResponseDto createList(ContentRequestDto requestDto) {
        //ai서버 파이썬에 보내(requsetDto.getContent())

        String content = requestDto.getContent();
        content += "이렇게 리스트로 만듬.";

        return new ContentResponseDto(content);
    }

    public ContentResponseDto createSummary(ContentRequestDto requestDto){
        String content = requestDto.getContent();
        content += "이렇게 요약글을 만듦";

        return new ContentResponseDto(content);
    }

    public ContentResponseDto createExpansion(ContentRequestDto requestDto){
        String content = requestDto.getContent();
        content += "이렇게 확장글을 만듦";

        return new ContentResponseDto(content);
    }

    public ContentResponseDto createEdit(ContentRequestDto requestDto){
        String content = requestDto.getContent();
        content += "이렇게 수정글을 만듦";

        return new ContentResponseDto(content);
    }

    public ContentResponseDto createUserResponse(UserRequestDto userRequestDto){
        String request = userRequestDto.getRequest();
        String content = userRequestDto.getContent();

        //sdfsdfsdfsd(requset, content);
        String response = "이렇게 응답이 생김";

        return new ContentResponseDto(response);
    }

    public ContentResponseDto createAnswer(UserRequestDto userRequestDto){
        String request = userRequestDto.getRequest();
        String content = userRequestDto.getContent();

        //sdfsdfsdfsd(requset, content);
        String response = "이렇게 답변이 생김";

        return new ContentResponseDto(response);
    }


}

