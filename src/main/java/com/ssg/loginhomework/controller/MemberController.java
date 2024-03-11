package com.ssg.loginhomework.controller;


import com.ssg.loginhomework.dao.MemberDAO;
import com.ssg.loginhomework.domain.MemberVO;
import com.ssg.loginhomework.dto.MemberDTO;
import com.ssg.loginhomework.service.MemberService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// /*로 전체 경로 설정 후 아래에서 if~else if문 사용하여 url 경로마다 다른 코드 실행
@Slf4j
@WebServlet(name = "memberController", urlPatterns = "/mem/*")
public class MemberController extends HttpServlet {

    private final MemberDAO dao = new MemberDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        log.info(pathInfo);

        if (pathInfo != null && pathInfo.contains("/deleteMember")) { // /mem/deleteMember 요청인 경우
            String id = req.getParameter("id");
            try {
                dao.deleteMember(id); // 회원 삭제 처리
                resp.setStatus(HttpServletResponse.SC_OK); //200 OK 응답
                resp.getWriter().write("삭제 성공");
                log.info("회원 삭제 성공: ID=" + id); //삭제된 id 콘솔창에 로그 찍음
                resp.sendRedirect("/mem/do"); // 목록 화면으로 리다이렉트
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("회원 삭제 중 오류 발생: " + e.getMessage());
                log.error("회원 삭제 중 오류 발생", e);
            }
        } else if (pathInfo.equals("/do")) { // /mem.do 인 경우
            try {
                Optional<List<MemberVO>> members = dao.listMembers();
                if (members.isPresent()) { //멤버가 존재할 경우 아래 jsp실행
                    req.setAttribute("members", members.get());
                    req.getRequestDispatcher("/WEB-INF/member/listMembers.jsp").forward(req, resp);
                } else { //멤버가 없을 경우에 jsp
                    req.getRequestDispatcher("/WEB-INF/member/noMember.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
        } else if (pathInfo.equals("/add")) { // /mem/add 요청인 경우
            req.getRequestDispatcher("/WEB-INF/member/addMember.do.jsp").forward(req, resp);
        } else if (pathInfo.contains("/modify")) { //mem/modify 요청인 경우
            try {
                MemberDTO dto = new MemberDTO();
                req.setAttribute("dto", dto);
                req.getRequestDispatcher("/WEB-INF/member/modifyMember.jsp").forward(req, resp);
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("회원 수정 폼 로딩 중 오류 발생");
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("해당 경로를 찾을 수 없습니다.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/add")) { // /mem/add 요청인 경우
            try {
                String id = req.getParameter("id");
                if (dao.isIdExists(id)) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                    resp.getWriter().write("이미 존재하는 아이디입니다.");
                    resp.flushBuffer();
//                    Thread.sleep(5000); 테스트용으로 쓰레드 대기시간 부여한거
                    return;
                }
                MemberDTO memberDTO = MemberDTO.builder()
                        .id(req.getParameter("id"))
                        .password(req.getParameter("password"))
                        .name(req.getParameter("name"))
                        .email(req.getParameter("email"))
                        .build();

                dao.addMember(memberDTO);
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("회원 생성 완료");
                resp.sendRedirect(req.getContextPath() + "/mem/do");
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("회원 생성 실패");
                e.printStackTrace();
            }
        } else if (pathInfo.contains("/modify")) {
            try {
                MemberDTO dto = new MemberDTO();
                dto.setId(req.getParameter("id"));
                dto.setName(req.getParameter("name"));
                dto.setEmail(req.getParameter("email"));
                dto.setPassword(req.getParameter("password"));

                dao.modifyMember(dto);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("회원 수정 완료");
                resp.sendRedirect(req.getContextPath() + "/mem/do");
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("회원 수정 실패");
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("해당 경로를 찾을 수 없습니다.");
        }
    }
}