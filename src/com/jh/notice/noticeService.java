package com.jh.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jh.action.Action;
import com.jh.action.actionForward;

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
		if(kind == null) {
			kind = "title";
		}else if(kind.equals("c")) {
			kind = "contents";
		}else if(kind.equals("w")) {
			kind = "writer";
		}else {
			kind = "title";
		}
		
		String search = request.getParameter("search");
		if(search==null) {
			search = "";
		}
		
		///////////////////////////////////////////////
		//1. StartRow, lastRow
		int perPage=10;
		int startRow = (curPage-1)*perPage+1; 
		int lastRow = curPage*perPage;
		
		//2. 페이징처리
		try {
			//1. 전체 글의 갯수
			int totalCount = noticedao.getTotalCount(kind, search);
			//2. 전체페이지 갯수
			int totalPage = totalCount/perPage;
			if(totalCount%perPage != 0 ) {
				totalPage++;
			}
			//3. 전체 블록갯수
			int perBlock = 5;
			int totalBlock = totalPage/perBlock;
			if(totalPage%perBlock !=0) {
				totalBlock++;
			}
			//4. 현재블록
			int curBlock = curPage/perBlock;
			if(curPage%perBlock !=0) {
				curBlock++;
			}
			//5. startNum,lastNum
			int startNum = (curBlock-1)*perBlock+1;
			int lastNum = curBlock*perBlock;
			
			//6. 현재블록이 마지막일때 
			if(curBlock == totalBlock) {
				lastNum = totalPage;
			}
			
			ArrayList<noticeDTO> ar =  noticedao.selectList(kind, search, startRow, lastRow);
			request.setAttribute("list", ar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		actionforward.setCheck(true);
		actionforward.setPath("../WEB-INF/views/notice/noticeList.jsp");
		return actionforward;
	}
	@Override
	public actionForward select(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		actionforward.setCheck(true);
		actionforward.setPath("../WEB-INF/views/notice/noticeSelect.jsp");
		return actionforward;
	}
	@Override
	public actionForward insert(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		actionforward.setCheck(true);
		actionforward.setPath("../WEB-INF/views/notice/noticeWrite.jsp");
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
