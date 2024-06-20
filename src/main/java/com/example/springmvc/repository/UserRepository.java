package com.example.springmvc.repository;

import com.example.springmvc.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}
