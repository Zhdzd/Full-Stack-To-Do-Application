package com.example.day2TodoApp.repository;

public class SQL {
    
    public static final String SQL_GET_ALL_TASKS = "select * from tasks";
    public static final String SQL_INSERT_TASKS = "insert into tasks (user_name, task_name, priority, due_date) values (?, ?, ?, ?)";
}
