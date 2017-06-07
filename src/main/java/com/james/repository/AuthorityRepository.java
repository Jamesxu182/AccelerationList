package com.james.repository;

import com.james.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by james on 6/3/17.
 */
@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
