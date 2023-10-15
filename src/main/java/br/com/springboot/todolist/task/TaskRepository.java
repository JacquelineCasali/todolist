package br.com.springboot.todolist.task;

import br.com.springboot.todolist.user.UserModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.UUID;


public interface TaskRepository extends JpaRepository <TaskModel, UUID>{
   List<TaskModel>findByIdUser(UUID idUser);
}


