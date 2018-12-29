package com.board.obk_board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    Long bno;
    String content;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class update{
        Long bno;
        String content;
        String writer;
    }
}
