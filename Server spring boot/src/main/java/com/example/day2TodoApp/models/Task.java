package com.example.day2TodoApp.models;


import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Task {
    private int tid;
    private String userName;
    private String taskName;
    private String priority;
    //public Date dueDate;
    private String dueDate;

    public Task(){
        
    }

    public Task(String userName, String taskName, String priority, String dueDate) {
        this.userName = userName;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    
    public String getDueDate() {
        return dueDate;
    }

    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public static Task populate(SqlRowSet rs){
        final Task task = new Task();
         task.setTid(rs.getInt("tid"));
         task.setUserName(rs.getString("user_name"));
         task.setTaskName(rs.getString("task_name"));
         task.setPriority(rs.getString("priority"));
         task.setDueDate(rs.getString("due_date"));
         return task;
    }
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("tid", tid)
                .add("userName", userName)
                .add("taskName", taskName)
                .add("priority", priority)
                .add("dueDate", dueDate)
                .build();
    }

    @Override
    public String toString(){
        return "Task [tid = " + tid + ", userName= " + userName;
    }



}
