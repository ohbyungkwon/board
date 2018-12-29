package com.board.obk_board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
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
@EntityListeners(value = {AuditingEntityListener.class})//for register, modify date
public class User {
    @Id
    @Column
    @GeneratedValue
    private Long seq;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    @CreatedDate
    private Date registerDate;

    @Column
    @LastModifiedDate
    private Date modifyDate;
}
