package com.board.obk_board.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "board")
@EntityListeners(value = {AuditingEntityListener.class})
public class Comment {
    @Id
    @Column
    @GeneratedValue
    private Long seq;

    @Column
    private String content;

    @CreatedDate
    @Column
    private Date registerDate;

    @LastModifiedDate
    @Column
    private Date modifyDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_seq", referencedColumnName = "seq")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "board_seq", referencedColumnName = "seq")
    private Board board;
}
