package com.combaksa.sklog.service;

import com.combaksa.sklog.dto.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SkLogApiService {
    private final String fastApiBaseUrl = "http://localhost:8080";

    private final RestTemplate restTemplate; //외부 API 호출 담당
    private final ObjectMapper objectMapper; //객체 -> json 변경 담당

    public String requestToFastApi(String requestBody, String endpoint, HttpMethod httpMethod){
        // http request 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // http request 헤더와 바디를 합쳐서 httpEntity 생성
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        // http request url 설정
        String apiUrl = fastApiBaseUrl + endpoint;

        // http request 전송
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, httpMethod, httpEntity, String.class);

        // http response body 내용 추출
        String responseBody = responseEntity.getBody();

        return responseBody;
    }

    public ContentResponseDto createTable(ContentRequestDto requestDto) {
        try {
            String requestBody = objectMapper.writeValueAsString(requestDto);
            String content = requestToFastApi(requestBody, "/api/list", HttpMethod.POST);

            return new ContentResponseDto(content);

        }catch (JsonProcessingException e) {
                // 객체 -> json 변경시 예외처리
                e.printStackTrace();
                return null;
            }
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

