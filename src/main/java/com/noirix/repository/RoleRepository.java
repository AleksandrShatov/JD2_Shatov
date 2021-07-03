package com.noirix.repository;

import com.noirix.domain.Role;
import com.noirix.domain.User;

import java.util.List;

public interface RoleRepository extends CrudOperations<Long, Role> {

    List<Role> getUserRoles(User user);
}
