package com.combaksa.sklog.service;

import com.combaksa.sklog.domain.RequestHistory;
import com.combaksa.sklog.dto.*;

import com.combaksa.sklog.repository.RequestHistoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkLogApiService {
    private final String fastApiBaseUrl = "http://localhost:8000";

    private final RestTemplate restTemplate; //외부 API 호출 담당
    private final ObjectMapper objectMapper; //객체 -> json 변경 담당
    private final RequestHistoryRepository historyRepository;

    private <T> T requestToFastApi(String requestBody, String endpoint, HttpMethod httpMethod, Class<T> responseType) {
        // http request 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // http request 헤더와 바디를 합쳐서 httpEntity 생성
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        // http request url 설정
        String apiUrl = fastApiBaseUrl + endpoint;

        // http request 전송
        ResponseEntity<T> responseEntity = restTemplate.exchange(apiUrl, httpMethod, httpEntity, responseType);

        // http response body 내용 추출
        T responseBody = responseEntity.getBody();

        return responseBody;
    }
    /* 표 생성 함수 */
    public UserResponseDto createTable(UserRequestDto requestDto) {
        try {
            // request 내용 작성
            requestDto.setRequest("Change the contents below to Markdown table format.");

            // 동일한 질문의 이전 기록을 확인
            List<RequestHistory> historyList = historyRepository.findAllByRequest(requestDto.getContent());

            List<String> userContentList = new ArrayList<>();
            List<String> aiContentList = new ArrayList<>();

            for (int i=0; i<2 && i < historyList.size(); i++){
                RequestHistory history = historyList.get(i);
                userContentList.add(history.getUserContent());
                aiContentList.add(history.getAiContent());
            }

            requestDto.setUserContentExamples(userContentList);
            requestDto.setAiContentExamples(aiContentList);

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            UserResponseDto responseDto = requestToFastApi(requestBody, "/query", HttpMethod.POST, UserResponseDto.class);

            // 요청 내용 DB에 저장
            RequestHistory newHistory = requestDto.toEntity();
            newHistory.setAiContent(responseDto.getContent());
            historyRepository.save(newHistory);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createList(UserRequestDto requestDto) {
        try {
            // request 내용 작성
            requestDto.setRequest("Change the contents below to markdown list format.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            UserResponseDto responseDto = requestToFastApi(requestBody, "/query", HttpMethod.POST, UserResponseDto.class);

            System.out.println(responseDto.getContent());
            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createSummary(UserRequestDto requestDto){
        try {
            // request 내용 작성
            requestDto.setRequest("Summarize the contents below. ");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            UserResponseDto responseDto = requestToFastApi(requestBody, "/query", HttpMethod.POST, UserResponseDto.class);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createExpansion(UserRequestDto requestDto){
        try {
            // request 내용 작성
            requestDto.setRequest("Please fill out the information below in more detail.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            UserResponseDto responseDto = requestToFastApi(requestBody, "/query", HttpMethod.POST, UserResponseDto.class);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createEdit(UserRequestDto requestDto){
        try {
            // request 내용 작성
            requestDto.setRequest("If there is anything I wrote incorrectly or misunderstood in the content below, please correct it."
                    + "Mark the modified content in red in Markdown format.");

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            UserResponseDto responseDto = requestToFastApi(requestBody, "/query", HttpMethod.POST, UserResponseDto.class);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDto createUserResponse(UserRequestDto requestDto){
        try {
            // 동일한 질문의 이전 기록을 확인
            List<RequestHistory> historyList = historyRepository.findAllByRequest(requestDto.getRequest());

            List<String> userContentList = new ArrayList<>();
            List<String> aiContentList = new ArrayList<>();

            for (int i=0; i<2 && i < historyList.size(); i++){
                RequestHistory history = historyList.get(i);
                userContentList.add(history.getUserContent());
                aiContentList.add(history.getAiContent());
            }

            requestDto.setUserContentExamples(userContentList);
            requestDto.setAiContentExamples(aiContentList);

            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);
            System.out.println(requestBody);

            // FastAPI에게 요청 후 응답 내용 저장
            UserResponseDto responseDto = requestToFastApi(requestBody, "/query", HttpMethod.POST, UserResponseDto.class);

            // 요청 내용 DB에 저장
            RequestHistory newHistory = requestDto.toEntity();
            newHistory.setAiContent(responseDto.getContent());
            historyRepository.save(newHistory);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public ChatBotAnswerDto createAnswer(ChatBotQuestionDto requestDto){
        try {
            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            ChatBotAnswerDto responseDto = requestToFastApi(requestBody, "/ai/chat-bot",
                    HttpMethod.POST, ChatBotAnswerDto.class);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }
    }

    public MetaDataResponseDto createMetaData(MetaDataRequestDto requestDto){
        try {
            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            MetaDataResponseDto responseDto = requestToFastApi(requestBody, "/ai/metadata",
                    HttpMethod.POST, MetaDataResponseDto.class);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }

    }

    public CohesionResponseDto createCohesion(CohesionRequestDto requestDto){
        try {
            // request Body 내용 생성: DTO객체를 -> json으로 변경
            String requestBody = objectMapper.writeValueAsString(requestDto);

            // FastAPI에게 요청 후 응답 내용 저장
            CohesionResponseDto responseDto = requestToFastApi(requestBody, "/ai/cohesion",
                    HttpMethod.POST, CohesionResponseDto.class);

            // 응답 내용
            return responseDto;

        }catch (JsonProcessingException e) {
            // 객체 -> json 변경시 예외처리
            e.printStackTrace();
            return null;
        }

    }


}

