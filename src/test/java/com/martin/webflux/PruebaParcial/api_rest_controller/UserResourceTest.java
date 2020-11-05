package com.martin.webflux.PruebaParcial.api_rest_controller;

import com.martin.webflux.PruebaParcial.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.BodyInserters;


import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
class UserResourceTest {
    int[] list = new int[]{12,5};


    @Autowired
    private RestService restService;
    @Test
    void create() {
        UserDto userDto = new UserDto( 123654654L, "name", "type", "user", false,this.list);
        String get = this.restService.restbuilder().post()
                .uri(UserResource.USERS).body(BodyInserters.fromValue(userDto))
                .exchange().expectStatus().isCreated().expectBody(String.class).returnResult().getResponseBody();

        assertNotNull(get);
        assertEquals("\"user created\"", get);
    }

    @Test
    void readUser() {
        UserDto userDto = this.restService.restbuilder().get()
                .uri(UserResource.USERS+UserResource.ID,123654654L)
                .exchange().expectStatus().isOk().expectBody(UserDto.class)
                .returnResult().getResponseBody();
        assertNotNull(userDto);
        assertEquals( 123654654L, userDto.getId());
    }

    @Test
    void searchAll() {
        this.restService.restbuilder().get().uri(UserResource.USERS).exchange().expectStatus().isOk();
    }

    @Test
    void updateUser() {
        UserDto userDto = new UserDto( 123654654L, "martin", "type", "user", false, list);
        String get = this.restService.restbuilder().put()
                .uri(UserResource.USERS+UserResource.ID, 123654654L)
                .body(BodyInserters.fromValue(userDto))
                .exchange().expectStatus().isAccepted().expectBody(String.class).returnResult().getResponseBody();
        assertNotNull(get);
        assertEquals("\"user edited\"", get);
    }

    @Test
    void deleteUser() {
        String user = this.restService.restbuilder().delete()
                .uri(UserResource.USERS+UserResource.ID, 123654654L)
                .exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();
    }
}