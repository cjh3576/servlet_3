package com.jh.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jh.action.Action;
import com.jh.action.actionForward;
import com.jh.img.ImgDAO;
import com.jh.img.ImgDTO;
import com.jh.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberService implements Action {
	private memberDAO memberdao;
	private ImgDAO imgDAO;
	public MemberService() {
		memberdao = new memberDAO();
		imgDAO = new ImgDAO();
	}
	@Override
	public actionForward list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//로그인
	@Override
	public actionForward select(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		actionForward actionforward = new actionForward();
		
		String method = request.getMethod();
		boolean check = true;
		String path="../WEB-INF/views/member/memberLogin.jsp";
		
		if(method.equals("POST")) {
			memberDAO memberdao = new memberDAO();
			memberDTO memberdto = new memberDTO();
			
			
			//체크박스 값 확인
			String checkbox = request.getParameter("check");
			//쿠키생성,
			if(checkbox != null){
				Cookie c = new Cookie("check",request.getParameter("id"));
				c.setMaxAge(60*60*24*7);
				response.addCookie(c);
			} else{
				Cookie c = new Cookie("check","");
				response.addCookie(c);
			}
			
			memberdto.setId(request.getParameter("id"));
			memberdto.setPw(request.getParameter("pw"));

			
			try {
				memberdto = memberdao.memberLogin(memberdto);
				if(memberdto != null){
					request.getSession().setAttribute("session", memberdto);
					check = false;
					path="../index.do";
				}else{
					
					request.setAttribute("message", "Login Fail");
					request.setAttribute("path", "./memberLogin");
					check = true;
					path = "../WEB-INF/views/common/result.jsp";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		actionforward.setCheck(check);
		actionforward.setPath(path);
		return actionforward;
	}

	//회원가입 insert
	@Override
	public actionForward insert(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		
		String method = request.getMethod();
		boolean check = true;
		String path="../WEB-INF/views/member/memberJoin.jsp";
		if(method.equals("POST")) {
			memberDTO memberdto = new memberDTO();
			String saveDirectory = request.getServletContext().getRealPath("images");
			int maxPostSize = 1024*1024*10;
			String encoding = "UTF-8";
			
			MultipartRequest multi = null;
			try {
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//파일 저장
			//hdd 저장이름
			String fileName = multi.getFilesystemName("f1");
			//클라이언트 업로드 이름
			String oName = multi.getOriginalFileName("f1");
			
			ImgDTO imgDTO = new ImgDTO();
			imgDTO.setFname(fileName);
			imgDTO.setOname(oName);
			
			memberdto.setId(multi.getParameter("id"));
			memberdto.setPw(multi.getParameter("pw"));
			memberdto.setName(multi.getParameter("name"));
			memberdto.setAge(Integer.parseInt(multi.getParameter("age")));
			memberdto.setPhone(multi.getParameter("phone"));
			memberdto.setEmail(multi.getParameter("email"));
			
			int result = 0;
			Connection con = null;
			
			try {
				con = DBConnector.getConnect();
				//auto commit 해제
				con.setAutoCommit(false);
				
				result = memberdao.memberJoin(memberdto,con);
				
				
				imgDTO.setId(multi.getParameter("id"));
				result = imgDAO.insert(imgDTO, con);
				if(result<1) {
					throw new Exception();
				}
				con.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result = 0;
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//post 끝
			if(result>0) {
				check = false;
				path="../index.do";
			}else {
				request.setAttribute("message", "Join Fail");
				request.setAttribute("path", "./memberJoin");
				check = true;
				path = "../WEB-INF/views/common/result.jsp";
			} 
		}
		actionforward.setCheck(check);
		actionforward.setPath(path);
		
		return actionforward;
	}
	public actionForward logout(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		request.getSession().invalidate();
		actionforward.setCheck(false);
		actionforward.setPath("../index.do");
		
		return actionforward;
	}
	
	@Override
	public actionForward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public actionForward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
