package br.com.springproject.todolist.task;

import br.com.springproject.todolist.repository.ITaskRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    @Autowired
    private ITaskRepository repository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio/termino deve ser maior que a data atual!");

        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inicio deve ser antes da data de termino!");

        }



        var task = this.repository.save(taskModel);
        return ResponseEntity.status(200).body(task);
    }


}
