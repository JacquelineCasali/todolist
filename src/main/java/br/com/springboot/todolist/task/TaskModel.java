package br.com.springboot.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 
 * ID
 * Usuário (ID_USUARIO)
 * Descrição
 * Título
 * Data de Início
 * Data de término
 * Prioridade
 * 
 */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descricao;
//tamanho maximo
    @Column(length = 50)
    private String titulo;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String prioridade;

    //asociar usuario com a tarefa
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitulo (String titulo) throws Exception {
        if (titulo.length() > 50) {
            throw new Exception("O campo titulo deve conter no máximo 50 caracteres");
        }
        this.titulo = titulo;
    }

}
