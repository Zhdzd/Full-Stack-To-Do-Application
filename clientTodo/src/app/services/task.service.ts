import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, Observable } from 'rxjs';
import { Todo } from '../todo';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private url = 'http://localhost:8080/api/tasks';
  private postUrl ='http://localhost:8080/api/tasks/add';
  private proxyUrl ='/api/tasks';
  private proxyPostUrl = '/api/tasks/add';

  constructor(private http: HttpClient) { }

  getAllTask(): Observable<Todo[]>{
    return this.http.get<Todo[]>(this.proxyUrl);
  }

   addTask(userName: string, taskName: string,
        priority: string, dueDate: string
      ):Promise<any>{
    const params = new HttpParams()
           .set('userName', userName)
           .set('taskName', taskName)
           .set('priority', priority)
           .set('dueDate', dueDate);

      const headers = {
       headers: new HttpHeaders().set(
         'Content-Type','application/x-www-form-urlencoded',
       )
     };


     return lastValueFrom(
       this.http.post(
         this.proxyPostUrl,params.toString(), headers
       )
     )

   }

  // postTask(todo:Todo):Promise<void>{
  //   return lastValueFrom(this.http.post<void>(this.proxyPostUrl,todo))
  // }


}
