package com.example.day2TodoApp.repository;

import java.util.LinkedList;
import java.util.List;

import com.example.day2TodoApp.models.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static com.example.day2TodoApp.repository.SQL.*;

@Repository
public class TodoRepository {

    @Autowired
    private JdbcTemplate template;


    public List<Task> getAllTasks(){

        final List<Task>tasks= new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_TASKS);
        while(rs.next()){
            final Task task = Task.populate(rs);
            tasks.add(task);
        }
        return tasks;
        
    }

    public boolean postTask(final Task task){
        int added = template.update(
            SQL_INSERT_TASKS, task.getUserName(), task.getTaskName(),
            task.getPriority(), task.getDueDate());
        return added > 0;
    }
    
}
