package com.ssg.loginhomework.dao;

import com.ssg.loginhomework.domain.MemberVO;
import com.ssg.loginhomework.dto.MemberDTO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDAO {

    //NULL 반환 방지를 위한 optional 사용
    //전체 회원 리스트 조회
    public Optional<List<MemberVO>> listMembers() throws Exception{
        List<MemberVO> memberList = new ArrayList<>();

        String listSQL = "select * from mvc_member";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(listSQL);
        @Cleanup ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            MemberVO memberVO = MemberVO.builder()
                    .id(resultSet.getString(1))
                    .password(resultSet.getString(2))
                    .name(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .join_date(resultSet.getDate(5).toLocalDate())
                    .build();

            memberList.add(memberVO);
        }
        return memberList.isEmpty() ? Optional.empty() : Optional.of(memberList);
    }

    //회원 추가
    public void addMember(MemberDTO memberDTO) throws Exception{
        String addSQL = "INSERT INTO mvc_member (id, password, name , email, join_date) VALUES (?,?,?,?,NOW())";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(addSQL);
        pstmt.setString(1, memberDTO.getId());
        pstmt.setString(2, memberDTO.getPassword());
        pstmt.setString(3, memberDTO.getName());
        pstmt.setString(4, memberDTO.getEmail());

        pstmt.executeUpdate();
    }

    //회원 삭제
    public void deleteMember(String id) throws Exception{
        String deleteSQL = "Delete From mvc_member WHERE id = ? ";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(deleteSQL);
        pstmt.setString(1, id);
        int rows = pstmt.executeUpdate();

        if (rows ==0 ){
            System.out.println("회원 삭제 실패");
        }
    }

    //회원 수정
    public void modifyMember(MemberDTO memberDTO) throws Exception{
        String UpdateSQL = "UPDATE mvc_member SET password = ?, name = ? , email = ? WHERE id = ? ";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(UpdateSQL);
        pstmt.setString(1, memberDTO.getPassword());
        pstmt.setString(2, memberDTO.getName());
        pstmt.setString(3, memberDTO.getEmail());
        pstmt.setString(4, memberDTO.getId());

        pstmt.executeUpdate();
    }

    //아이디가 이미 있는경우 가입 실패
    public boolean isIdExists(String id) throws Exception{
        String existSQL = "SELECT COUNT(*) FROM mvc_member WHERE id = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(existSQL);
        pstmt.setString(1, id);
        @Cleanup ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            int count = rs.getInt(1);
            return count>0;
        }
        return false;
    }
}
