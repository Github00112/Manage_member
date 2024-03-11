package com.ssg.loginhomework.dto;


import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String password;
    private String name;
    private String email;
    private Date join_date;
}
