package me.dio.domain.repository;

import org.springframework.data.repository.CrudRepository;

import me.dio.domain.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

	boolean existsByAccountNumber(String accountNumber);
}