import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TaskService } from '../services/task.service';
import { Todo } from '../todo';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {


  userName!: string;
  taskName!: string;
  priority!: string;
  dueDate!: string;

  form!: FormGroup



  constructor(private fb: FormBuilder, private taskSvc: TaskService, private router:Router) { }

  ngOnInit(): void {
      this.form = this.createForm();
      console.info('>>>>form: ',this.form)
  }

  private createForm(): FormGroup{
       return  this.fb.group({
            userName: this.fb.control([''], [Validators.required]),
            taskName: this.fb.control([''],[Validators.required]),
            priority: this.fb.control(['low']),
            dueDate: this.fb.control([''])
          })
  }
  // processForm(){
  //   const todo = this.form.value as Todo
  //   this.taskSvc.postTask(todo)
  //     .then(() => this.router.navigate(['/']))
  //     .catch(err => alert(err));
  // }

   processForm(){
     this.taskSvc.addTask(this.form.value.userName, this.form.value.taskName,this.form.value.priority,
       this.form.value.dueDate)
         .then(submitted => {
           this.form.reset();
           console.info('>>> submitted', submitted);
         })
         .catch(error => {
           alert('error occurred')
           console.error('>>>error', error)
         })
   }
  //
  // processForm(){
  //   const taskForm = this.form.value as Todo;

  //   console.log(taskForm);

  //   this.taskSvc.addTask(this.form.value.userName,this.form.value.taskName,this.form.value.priority,this.form.value.dueDate)
  //     .then(() => this.router.navigate(['/']))
  //     .catch(err => alert(err))
  //     console.log('>>>>....ERROR ')



  // }

}
