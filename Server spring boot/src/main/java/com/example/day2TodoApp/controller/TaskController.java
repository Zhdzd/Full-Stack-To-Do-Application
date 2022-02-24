package com.example.day2TodoApp.controller;

import java.util.List;

import com.example.day2TodoApp.models.Task;
import com.example.day2TodoApp.repository.TodoRepository;
import com.mysql.cj.protocol.x.OkBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

@CrossOrigin
@RestController
@RequestMapping(path="/api/tasks", produces=MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Autowired
    private TodoRepository todoRepo;

    @GetMapping("")
    public ResponseEntity<String> getAllTasks(){
        List<Task> result = todoRepo.getAllTasks();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        result.stream().forEach(v -> arrBuilder.add(v.toJson()));

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    //consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(path="/add", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postTask(@RequestBody MultiValueMap<String,String> taskData){

        //log the task inserted from client
        System.out.println(taskData);
        
        //the data in "" depends on what i put in angular side
        Task task = new Task(taskData.get("userName").get(0), taskData.get("taskName").get(0),
                taskData.get("priority").get(0), taskData.get("dueDate").get(0));

        //task added successfully or not(true or false)
        boolean added = todoRepo.postTask(task);

        if(added){
            JsonObjectBuilder objBuilder = Json.createObjectBuilder()
                .add("message", "task inserted");
            ResponseEntity<String> re = 
                    new ResponseEntity<>(objBuilder.build().toString(), HttpStatus.ACCEPTED);
            return re;
        } else{
            JsonObjectBuilder errBuilder = Json.createObjectBuilder()
            .add("error", "task not accepted");
        ResponseEntity<String> re = 
                new ResponseEntity<>(errBuilder.build().toString(), HttpStatus.BAD_REQUEST);
                return re;
        }



       



    }
    
}
