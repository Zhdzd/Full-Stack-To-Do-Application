import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TaskListComponent } from './components/task-list.component';
import { TaskService } from './services/task.service';
import { MainComponent } from './components/main.component';
import { HttpClientModule} from '@angular/common/http';
import { AddTaskComponent } from './components/add-task.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {path: 'add', component: AddTaskComponent},
  {path: 'tasks' , component: TaskListComponent},
  {path: '**', redirectTo: '/tasks', pathMatch:'full'}

]
@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent,
    MainComponent,
    AddTaskComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    FormsModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
