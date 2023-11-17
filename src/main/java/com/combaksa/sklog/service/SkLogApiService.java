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
    private final String fastApiBaseUrl = "http://localhost:8000";

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

    /* 표 생성 함수 */
    public UserResponseDto createTable(UserRequestDto requestDto) {
        try {
            // request 내용 작성
            requestDto.setRequest("아래 내용 마크다운의 표 형식으로 변경해줘.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            String content = requestToFastApi(requestBody, "/query", HttpMethod.POST);

            // 응답 내용
            return new UserResponseDto(content);

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createList(UserRequestDto requestDto) {
        try {
            // request 내용 작성
            requestDto.setRequest("아래 내용 마크다운 리스트 형식으로 변경해줘.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            String content = requestToFastApi(requestBody, "/query", HttpMethod.POST);

            // 응답 내용
            return new UserResponseDto(content);

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createSummary(UserRequestDto requestDto){
        try {
            // request 내용 작성
            requestDto.setRequest("아래 내용 요약해줘.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            String content = requestToFastApi(requestBody, "/query", HttpMethod.POST);

            // 응답 내용
            return new UserResponseDto(content);

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createExpansion(UserRequestDto requestDto){
        try {
            // request 내용 작성
            requestDto.setRequest("아래 내용 더 자세히 작성해줘.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            String content = requestToFastApi(requestBody, "/query", HttpMethod.POST);

            // 응답 내용
            return new UserResponseDto(content);

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createEdit(UserRequestDto requestDto){
        try {
            // request 내용 작성
            requestDto.setRequest("아래 작성한 내용중에 내가 잘못 작성하거나 이해를 잘못한 부분이 있다면 수정해줘." +
                    "수정한 내용은 마크다운 형식으로 빨간색으로 표시해줘.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            String content = requestToFastApi(requestBody, "/query", HttpMethod.POST);

            // 응답 내용
            return new UserResponseDto(content);

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createUserResponse(UserRequestDto requestDto){
        try {
            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            String content = requestToFastApi(requestBody, "/query", HttpMethod.POST);

            // 응답 내용
            return new UserResponseDto(content);

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public ContentResponseDto createAnswer(UserRequestDto userRequestDto){
        String request = userRequestDto.getRequest();
        String content = userRequestDto.getContent();

        //sdfsdfsdfsd(requset, content);
        String response = "이렇게 답변이 생김";

        return new ContentResponseDto(response);
    }


}

