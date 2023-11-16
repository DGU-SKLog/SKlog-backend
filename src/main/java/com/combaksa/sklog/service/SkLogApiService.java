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
        //ai서버 파이썬에 보내(requsetDto.getContent())

        String content = requestDto.getContent();
        content += "이렇게 리스트로 만듬.";

        return new UserResponseDto(content);
    }

    public UserResponseDto createSummary(UserRequestDto requestDto){
        String content = requestDto.getContent();
        content += "이렇게 요약글을 만듦";

        return new UserResponseDto(content);
    }

    public UserResponseDto createExpansion(UserRequestDto requestDto){
        String content = requestDto.getContent();
        content += "이렇게 확장글을 만듦";

        return new UserResponseDto(content);
    }

    public UserResponseDto createEdit(UserRequestDto requestDto){
        String content = requestDto.getContent();
        content += "이렇게 수정글을 만듦";

        return new UserResponseDto(content);
    }

    public UserResponseDto createUserResponse(UserRequestDto userRequestDto){
        String request = userRequestDto.getRequest();
        String content = userRequestDto.getContent();

        System.out.println(request);
        System.out.println(content);

        //sdfsdfsdfsd(requset, content);
        String response = "이렇게 응답이 생김";

        return new UserResponseDto(response);
    }

    public ContentResponseDto createAnswer(UserRequestDto userRequestDto){
        String request = userRequestDto.getRequest();
        String content = userRequestDto.getContent();

        //sdfsdfsdfsd(requset, content);
        String response = "이렇게 답변이 생김";

        return new ContentResponseDto(response);
    }


}

