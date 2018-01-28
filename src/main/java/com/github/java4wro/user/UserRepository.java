package com.github.java4wro.user;

import com.github.java4wro.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    User findOneByUuid(String uuid);

    boolean existsByEmail(String email);

    List<User> findAllByEnabledAndCreatedAtBefore(boolean enabled, Date before);
}
