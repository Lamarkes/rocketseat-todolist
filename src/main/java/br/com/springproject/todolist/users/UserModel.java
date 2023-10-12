package br.com.springproject.todolist.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID") // maneira de gerar valores UUID automatico - mais seguros que o id normal
    private UUID id;
    private String name;
    @Column(unique = true) // certifica que esse valor seja unico e nao se repita nas colunas - username sera unico e nao podera ser repetido
    private String username;
    private String password;

    @CreationTimestamp // assim que o banco for criado, ja salva as informaçoes
    private LocalDateTime createdAt; // registrar o momento que o dado ou a tabela foi criado

}
