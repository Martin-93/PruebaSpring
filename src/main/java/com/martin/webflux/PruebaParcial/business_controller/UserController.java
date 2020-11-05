package com.martin.webflux.PruebaParcial.business_controller;

import com.martin.webflux.PruebaParcial.documents.User;
import com.martin.webflux.PruebaParcial.dto.UserDto;
import com.martin.webflux.PruebaParcial.exceptions.NotFoundExceptions;
import com.martin.webflux.PruebaParcial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ResponseEntity> createUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setActive(userDto.getActive());
        user.setData(userDto.getData());
        user.setName(userDto.getName());
        user.setType(userDto.getType());
        return this.userRepository.save(user).map(callback -> {
            return new ResponseEntity("\"user created\"", HttpStatus.CREATED);
        }).onErrorReturn(new ResponseEntity( "\"user not created\"", HttpStatus.NOT_FOUND) );
    }

    public Mono<UserDto> findUserById(Long id) {
       return userRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundExceptions("user"+id)))
               .map(UserDto::new);
    }

    public Flux<UserDto> searchAll() {
        return this.userRepository.findAll().map(UserDto::new);
    }

   public Mono<ResponseEntity> editUser(Long id, UserDto userDto){
        Mono<User> user = this.userRepository.findById(id).switchIfEmpty( Mono.error(new NotFoundExceptions("user"+id)))
                .map(userDB->{
                    userDB.setType(userDto.getType());
                    userDB.setName(userDto.getName());
                    userDB.setData(userDto.getData());
                    userDB.setActive(userDto.getActive());
                    return userDB;
                });
        return Mono.when(user)
                .then(this.userRepository.saveAll(user)
                .next().map(callback-> new ResponseEntity("\"user edited\"", HttpStatus.ACCEPTED)));
   }

   public Mono<ResponseEntity> deleteUser(Long id) {
       Mono<UserDto> user =userRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundExceptions("user"+id)))
               .map(UserDto::new);
       return Mono.when(user)
               .then(this.userRepository.deleteById(id).map(callback -> new ResponseEntity("\"user deleted\"", HttpStatus.ACCEPTED)));
   }


}