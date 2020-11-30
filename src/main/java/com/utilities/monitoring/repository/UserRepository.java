package com.utilities.monitoring.repository;

import com.utilities.monitoring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
