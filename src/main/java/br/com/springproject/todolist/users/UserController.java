package br.com.springproject.todolist.users;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.springproject.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository repository;

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserModel userModel){
        var user = this.repository.findByUsername(userModel.getUsername());
        if (user != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");

        }
        // lib de criptografia de senha - adiciona mais uma camada de segurança para o sistema
        var password = BCrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());
        userModel.setPassword(password);
        this.repository.save(userModel);
        return ResponseEntity.status(200).body("Usuario criado");
    }
}
