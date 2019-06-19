package com.gsf.todo.api.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.gsf.todo.model.Task;
import com.gsf.todo.repository.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TaskControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void save_emptyDescription_400() throws JSONException {

        String taskInJson = "{\"status\":\"PENDING\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(taskInJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/todo", entity, String.class);

        String expectedJson = "{\"status\":400,\"errors\":[\"Please provide a description.\"]}";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

        verify(taskRepository, times(0)).save(any(Task.class));
        
    }
    
    @Test
    public void save_taskSucess_201() throws JSONException {
    	
    	String taskInJson = "{\"description\":\"Task to do\", \"status\":\"PENDING\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(taskInJson, headers);

        ResponseEntity<String> response = restTemplate.exchange("/todo", HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }
    
    @Test
    public void edit_taskSucess_200() throws JSONException {
    	
    	String taskInJson = "{\"id\":\"1\", \"description\":\"Task to do\", \"status\":\"PENDING\"}";
    	Long id = 1L;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(taskInJson, headers);

        ResponseEntity<String> response = restTemplate.exchange("/todo/"+id, HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_taskSucess_200() throws JSONException {
    	
    	String taskInJson = "{\"id\":\"1\", \"description\":\"Task to do\", \"status\":\"PENDING\"}";
    	Long id = 1L;
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<String> entity = new HttpEntity<>(taskInJson, headers);
    	
    	ResponseEntity<String> response = restTemplate.exchange("/todo/"+id, HttpMethod.DELETE, entity, String.class);
    	
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

   
}
