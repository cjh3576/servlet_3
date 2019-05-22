package com.jh.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jh.action.Action;
import com.jh.action.actionForward;
import com.jh.page.SearchMakePage;
import com.jh.page.SearchPager;
import com.jh.page.SearchRow;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class noticeService implements Action{
	private noticeDAO noticedao;
	public noticeService(){
		noticedao = new noticeDAO();
	}
	
	actionForward actionforward = new actionForward();
	@Override
	public actionForward list(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		
		int curPage = 1;
		try {
		curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		//c,w,t
		SearchMakePage s = new SearchMakePage(curPage, kind, search);
		
		//1. row
		SearchRow searchRow = s.makeRow();
		ArrayList<noticeDTO> ar = null;
		try {
			ar = noticedao.selectList(searchRow);
			//2. page
			int totalCount = noticedao.getTotalCount(searchRow);
			SearchPager searchPager = s.makePage(totalCount);
			
			request.setAttribute("pager", searchPager);
			request.setAttribute("list", ar);
			actionforward.setCheck(true);
			actionforward.setPath("../WEB-INF/views/notice/noticeList.jsp");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Server Error");
			request.setAttribute("path", "../index.do");
			actionforward.setCheck(true);
			actionforward.setPath("../WEB-INF/views/common/result.jsp");
		}
		
		return actionforward;
	}
	@Override
	public actionForward select(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		
		//없으면 삭제되었거나 없는글 alert 후 리스트로
		noticeDTO noticedto = null;
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			noticedto = noticedao.selectOne(num);
			noticedao.updateHit(num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path="";
		if(noticedto != null) {
			request.setAttribute("noticedto", noticedto);
			path = "../WEB-INF/views/notice/noticeSelect.jsp";
		}else {
			request.setAttribute("message", "No Data");
			request.setAttribute("path", "./noticeList");
			path="../WEB-INF/views/common/result.jsp";
		}
		
		actionforward.setCheck(true);
		actionforward.setPath(path);
		return actionforward;
	}
	@Override
	public actionForward insert(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		
		String method = request.getMethod(); //GET, POST
		boolean check = true;
		String path="../WEB-INF/views/notice/noticeWrite.jsp";
		
		if(method.equals("POST")) {
			noticeDTO noticedto = new noticeDTO();
			//1. request를 하나로 합치기
			String saveDirectory = request.getServletContext().getRealPath("upload");
			//System.out.println(saveDirectory);
			int maxPostSize = 1024*1024*10; //byte
			String encoding = "UTF-8";
					
			MultipartRequest multi = null;	
			try {
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //지 알아서 중복파일 바꿈
			//파일 저장 끝
			
			//Hdd에 저장된 이름(DB) 실제 저장된 이름
			String fileName = multi.getFilesystemName("f1");//파일의 파라미터 이름
			//클라이언트가 업로드 할 때 파일 이름
			String oName = multi.getOriginalFileName("f1"); //파일의 파라미터 이름
			
			
			noticedto.setTitle(multi.getParameter("title"));
			noticedto.setWriter(multi.getParameter("writer"));
			noticedto.setContents(multi.getParameter("contents"));
			int result = 0;
			try {
				result = noticedao.insert(noticedto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result>0) {
				check = false;
				path="./noticeList";
			}else {
				request.setAttribute("message", "Write Fail");
				request.setAttribute("path", "./noticeList");
				check = true;
				path = "../WEB-INF/views/common/result.jsp";
			}
			
		} //post 끝
		actionforward.setCheck(check);
		actionforward.setPath(path);
		
		
		
		
		return actionforward;
	}
	@Override
	public actionForward update(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		actionforward.setCheck(true);
		actionforward.setPath("../WEB-INF/views/notice/noticeUpdate.jsp");
		return actionforward;
	}
	@Override
	public actionForward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
