package br.com.springboot.todolist.controller;
import br.com.springboot.todolist.task.TaskModel;
import br.com.springboot.todolist.task.TaskRepository;
import br.com.springboot.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("tarefa")
public class TaskController {
    
/*String (texto)
Integer (int)numeros interitos
Double double Numeros 0.000
Float Numeros 0.000
char (A C)
Date (data)
void não tem retorno do metodo  */

    //metodo de cadastro
    // gerenciar
    @Autowired
    private TaskRepository taskRepository;
    @PostMapping
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){

   var idUser = request.getAttribute("idUser");
     taskModel.setIdUser((UUID) idUser);

     // validação de data
var criarData= LocalDateTime.now();
if(criarData.isAfter(taskModel.getStartAt()) || criarData.isAfter(taskModel.getEndAt())){
return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("A data de inicio ou a data de termino deve ser maior do que a data atual");
}
        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio deve ser menor do que a data de termino");
        }
       // verificando se o usuario existe
       var tarefa= this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }
    @GetMapping
public List<TaskModel> list(HttpServletRequest request){
        var idUser=request.getAttribute("idUser");
        var tasks=this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
}

//update
    @PutMapping("/{id}")
public TaskModel update (@RequestBody TaskModel taskModel, HttpServletRequest request,@PathVariable UUID id){
        var idUser=request.getAttribute("idUser");
     var task=  this.taskRepository.findById(id).orElse(null);
        Utils.copyNonNullProperties(taskModel,task);
             return this.taskRepository.save(task);
}
}
