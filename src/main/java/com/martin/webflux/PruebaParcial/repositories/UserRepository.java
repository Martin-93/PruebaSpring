package com.martin.webflux.PruebaParcial.repositories;

import com.martin.webflux.PruebaParcial.documents.User;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface UserRepository extends ReactiveSortingRepository<User, Long> {
}
