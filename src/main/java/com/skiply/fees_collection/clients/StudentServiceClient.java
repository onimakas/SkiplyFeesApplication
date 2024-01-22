package com.skiply.fees_collection.clients;

import com.skiply.fees_collection.dtos.StudentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.Optional;

@Component
public class StudentServiceClient {
    private final WebClient webClient;

    public StudentServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
    }

    public Optional<StudentDto> getStudentDetailsById(String studentId) {
        try {
            StudentDto studentDto = webClient.get()
                    .uri("/students/{id}", studentId)
                    .retrieve()
                    .bodyToMono(StudentDto.class)
                    .block();

            return Optional.ofNullable(studentDto);
        } catch (WebClientResponseException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            if (statusCode == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            } else {
                throw new RuntimeException("Error while fetching student details: " + statusCode.value(), ex);
            }
        }
    }
}
