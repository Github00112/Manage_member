package com.ssg.loginhomework.service;

import com.ssg.loginhomework.dao.MemberDAO;
import com.ssg.loginhomework.domain.MemberVO;
import com.ssg.loginhomework.dto.MemberDTO;
import com.ssg.loginhomework.util.MapperUtil;
import org.modelmapper.ModelMapper;

public enum MemberService {
    INSTACNE;
    private MemberDAO dao;

    private ModelMapper modelMapper;

    MemberService(){
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

}

