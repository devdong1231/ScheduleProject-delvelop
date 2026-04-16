package com.scheduleprojectdevelop.repository;

import com.scheduleprojectdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
