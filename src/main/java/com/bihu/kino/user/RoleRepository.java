package com.bihu.kino.user;


import com.bihu.kino.user.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, Long> {
    List<UserRole> findAll();
    UserRole findByRole(String role);
}
