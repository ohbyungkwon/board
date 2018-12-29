package com.board.obk_board.repository;

import com.board.obk_board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.seq=?1")
    Comment findCommentBySeq(Long id);
}
