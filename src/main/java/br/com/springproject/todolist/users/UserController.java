package br.com.springproject.todolist.users;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/")
    public void createUser(@RequestBody UserModel userModel){

        System.out.println(userModel.getUserName());

    }
}
