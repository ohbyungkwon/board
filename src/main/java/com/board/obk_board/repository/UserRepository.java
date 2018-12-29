package com.board.obk_board.repository;

import com.board.obk_board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username=?1")
    User findUserByEmail(String username);

    @Query("select u from User u where u.seq=?1")
    User findUserById(int seq);
}
