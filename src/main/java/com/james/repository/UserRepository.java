package com.james.repository;

import com.james.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by james on 6/1/17.
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}
