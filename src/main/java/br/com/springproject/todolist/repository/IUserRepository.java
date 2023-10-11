package br.com.springproject.todolist.repository;

import br.com.springproject.todolist.users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface IUserRepository extends JpaRepository<UserModel, UUID> {
}
