package br.com.springboot.todolist.controller;


import br.com.springboot.todolist.user.UserModel;
import br.com.springboot.todolist.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping("/users")
public class UserController {
    
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
    private UserRepository userRepository;
    @PostMapping
    public ResponseEntity create(@RequestBody UserModel userModel){
       // verificando se o usuario existe
       var user= this.userRepository.findByUsername(userModel.getUsername());

       if (user != null){

           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }
       // criptografando senha
        var passwordHashred = BCrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());
       userModel.setPassword(passwordHashred);
       var userCreate = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreate);


    }


}
