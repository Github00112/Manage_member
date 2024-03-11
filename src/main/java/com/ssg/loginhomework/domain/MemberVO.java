package com.ssg.loginhomework.domain;

import lombok.*;

import java.time.LocalDate;


@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberVO {
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDate join_date;
}
