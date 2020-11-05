package com.martin.webflux.PruebaParcial.api_rest_controller;

import com.martin.webflux.PruebaParcial.business_controller.UserController;
import com.martin.webflux.PruebaParcial.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/users";
    public static final String ID= "/{id}";

   private final UserController userController;

   @Autowired
    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @PostMapping(produces = {"application/json"})
    public Mono<ResponseEntity> create(@RequestBody UserDto userDto) {
       return this.userController.createUser(userDto);
    }

    @GetMapping(value = ID)
    public Mono<UserDto> readUser(@PathVariable Long id){
       return this.userController.findUserById(id);
    }

    @GetMapping
    public Flux<UserDto> getAllUsers(){
       return this.userController.searchAll();
    }

    @PutMapping(value = ID)
    public Mono<ResponseEntity> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

        return this.userController.editUser(id, userDto);
    }

    @DeleteMapping(value = ID)
    public Mono<ResponseEntity> deleteUser(@PathVariable Long id) {
       return this.userController.deleteUser(id);
    }
}
