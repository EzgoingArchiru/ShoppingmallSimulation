package io.github.ezgoingarchiru.shoppingmall.simulation.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean findUserByEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existsByEmail(String email);

    User getUserByEmail(String email);
}
