package com.project.service;

import com.project.exception.HttpException;
import com.project.model.Projekt;
import com.project.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final RestClient restClient;

    public StudentServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    private String getResourcePath() {
        return "/api/students";
    }

    private String getResourcePath(Integer id) {
        return String.format("%s/%d", getResourcePath(), id);
    }

    @Override
    public Optional<Student> getStudent(Integer studentId) {
        String resourcePath = getResourcePath(studentId);
        logger.info("REQUEST -> GET {}", resourcePath);
        Student student = restClient
                .get()
                .uri(resourcePath)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new HttpException(res.getStatusCode(), res.getHeaders());
                })
                .body(Student.class);
        return Optional.ofNullable(student);
    }

    @Override
    public void setStudent(Student student) {
        if (student.getStudentId() != null) {
            String resourcePath = getResourcePath(student.getStudentId());
            logger.info("REQUEST -> PUT {}", resourcePath);
            restClient
                    .put()
                    .uri(resourcePath)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(student)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new HttpException(res.getStatusCode(), res.getHeaders());
                    })
                    .toBodilessEntity();
        } else {
            String resourcePath = getResourcePath();
            logger.info("REQUEST -> POST {}", resourcePath);
            ResponseEntity<Void> response = restClient
                    .post()
                    .uri(resourcePath)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(student)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new HttpException(res.getStatusCode(), res.getHeaders());
                    })
                    .toBodilessEntity();
            URI location = response.getHeaders().getLocation();
            logger.info("REQUEST (location) -> GET {}", location);
	        assert location != null;
            restClient
                    .get()
                    .uri(location)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (req, res) -> {
                        throw new HttpException(res.getStatusCode(), res.getHeaders());
                    })
                    .body(Student.class);
        }
    }

    @Override
    public void deleteStudent(Integer studentId) {
        String resourcePath = getResourcePath(studentId);
        logger.info("REQUEST -> DELETE {}", resourcePath);
        restClient
                .delete()
                .uri(resourcePath)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new HttpException(res.getStatusCode(), res.getHeaders());
                })
                .toBodilessEntity();

    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        URI uri = ServiceUtil.getURI(getResourcePath(), pageable);
        logger.info("REQUEST -> GET {}", uri);
        return getPage(uri);
    }

    @Override
    public Page<Student> searchByNazwisko(String nazwisko, Pageable pageable) {
        URI uri = ServiceUtil
                .getUriComponent(getResourcePath(), pageable)
                .queryParam("nazwisko", nazwisko)
                .build().toUri();
        logger.info("REQUEST -> GET {}", uri);
        return getPage(uri);
    }

    @Override
    public Optional<Student> searchByIndex(String index) {
        URI uri = ServiceUtil
                .getUriComponent(getResourcePath(), Pageable.unpaged())
                .queryParam("index", index)
                .build().toUri();
        logger.info("REQUEST -> GET {}", uri);
        Student student = restClient
                .get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new HttpException(res.getStatusCode(), res.getHeaders());
                })
                .body(Student.class);
        return Optional.ofNullable(student);
    }

    private Page<Student> getPage(URI uri) {
        return restClient.get()
                .uri(uri.toString())
                .retrieve()
                .body(new ParameterizedTypeReference<RestResponsePage<Student>>(){});
    }
}
