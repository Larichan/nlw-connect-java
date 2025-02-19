package net.larichan.nlw_connect.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.larichan.nlw_connect.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByUserEmail(String email);
}
