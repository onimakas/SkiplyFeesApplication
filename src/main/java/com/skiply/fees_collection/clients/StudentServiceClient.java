/**
 * The StudentServiceClient class is responsible for interacting with the Student Service API to retrieve student details.
 */
package com.skiply.fees_collection.clients;

import com.skiply.fees_collection.dtos.StudentDto;
import com.skiply.fees_collection.exceptions.WebClientStudentRetrieveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.Optional;

@Component
public class StudentServiceClient {
    private final WebClient webClient;

    /**
     * Constructs a new StudentServiceClient with the provided WebClient builder.
     *
     * @param webClientBuilder the WebClient builder used to create the underlying WebClient instance.
     */

    public StudentServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
    }

    /**
     * Retrieves the student details for the given student ID.
     *
     * @param studentId the ID of the student to retrieve details for.
     * @return an Optional containing the StudentDto if found, or empty if not found.
     * @throws WebClientStudentRetrieveException if an error occurs while fetching the student details.
     */

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
                throw new WebClientStudentRetrieveException("Error while fetching student details: " + statusCode.value(), ex);
            }
        }
    }
}
