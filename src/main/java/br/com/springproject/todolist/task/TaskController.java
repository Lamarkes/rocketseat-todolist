package br.com.springproject.todolist.task;

import br.com.springproject.todolist.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private ITaskRepository repository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel){
        var task = this.repository.save(taskModel);
        return task;
    }


}
