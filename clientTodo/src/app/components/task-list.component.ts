import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';
import { Todo } from '../todo';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  todo: Todo[] = [];

  constructor(private taskSvc: TaskService) { }

  ngOnInit(): void {
    this.taskSvc.getAllTask().subscribe((todo) =>(this.todo = todo));
    console.log(this.todo);
  }

}
