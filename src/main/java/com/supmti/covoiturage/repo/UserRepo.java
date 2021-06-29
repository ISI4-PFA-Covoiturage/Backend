package com.supmti.covoiturage.repo;

import com.supmti.covoiturage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    void deleteUserById(Long id);

    User findUserById(Long id);
}
