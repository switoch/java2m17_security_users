package com.study.data.repository;

import com.study.data.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users,Long> {
    Users findByUsername(final String username);
}