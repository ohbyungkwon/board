package com.board.obk_board.dto;

import com.board.obk_board.domain.Board;
import com.board.obk_board.domain.User;
import com.board.obk_board.enums.BoardMessage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    @Data
    @Builder
    public static class create{
        String msg;
        String title;
        String content;
    }
    @Data
    @Builder
    public static class update{
        String msg;
        String title;
        String content;
    }

    @Data
    @Builder
    public static class delete{
        String msg;
    }
}
