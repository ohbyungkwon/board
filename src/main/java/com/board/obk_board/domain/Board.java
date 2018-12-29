package com.board.obk_board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EntityListeners(value = {AuditingEntityListener.class})//for register, modify date
public class Board {
    @Id
    @Column
    private Long seq;

    @Column
    private String writer;

    @Column
    private String title;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comment;
}
