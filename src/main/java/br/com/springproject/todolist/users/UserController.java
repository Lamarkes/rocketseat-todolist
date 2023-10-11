package br.com.springproject.todolist.users;


import br.com.springproject.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository repository;

    @GetMapping("/")
    public void createUser(@RequestBody UserModel userModel){

        System.out.println(userModel.getUserName());

    }
}
