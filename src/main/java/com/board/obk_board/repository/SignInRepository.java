package com.board.obk_board.repository;

import com.board.obk_board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignInRepository extends JpaRepository<User, Long> {
}
