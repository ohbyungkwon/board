package com.board.obk_board.repository;

import com.board.obk_board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select b.seq from Board b order by b.seq desc limit 1", nativeQuery = true)
    Long selectLastIndex();

    @Query(value = "select b.seq from Board b order by b.seq asc limit 1", nativeQuery = true)
    Long selectFirstIndex();

    @Query("select b from Board b where b.seq=?1")
    Board selectBaord(Long id);

//    @Query("select b from Board b where b.writer=?1")
//    Board selectBaordByWrtier(String writer);
}
