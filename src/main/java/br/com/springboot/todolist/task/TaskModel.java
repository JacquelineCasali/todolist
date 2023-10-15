package br.com.springboot.todolist.task;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

// estrutura da tabela
@Data
@Entity(name="tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
private String descricao;
// apenas 50 caracters no titulo
@Column(length = 50)
    private String titulo;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String prioridade;
    private UUID idUser;
    // quando o dado for gerado
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitulo(String titulo) throws Exception{
        if (titulo.length() > 50) {
            throw new Exception("O campo titulo deve conter no máximo 50 caracteres");
        }
        this.titulo=titulo;
    }
}
