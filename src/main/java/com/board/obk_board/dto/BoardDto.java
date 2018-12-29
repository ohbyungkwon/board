package com.board.obk_board.dto;

import com.board.obk_board.domain.Board;
import com.board.obk_board.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long totalPage;
    private Long firstIndex;
    private Long lastIndex;
    private Page<Board> board;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class create{
        private String title;
        private String contents;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class update{
        private String title;
        private String contents;
    }
}
