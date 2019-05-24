package com.jh.qna;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jh.action.Action;
import com.jh.action.actionForward;
import com.jh.page.SearchMakePage;
import com.jh.page.SearchPager;
import com.jh.page.SearchRow;
import com.jh.upload.UploadDAO;
import com.jh.upload.UploadDTO;
import com.jh.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaService implements Action {
	private QnaDAO qnadao;
	private UploadDAO uploadDAO;
	public QnaService() {
		qnadao = new QnaDAO();
		uploadDAO = new UploadDAO();
	}
	
	//list
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
		
		SearchMakePage s = new SearchMakePage(curPage, kind, search);
		
		//1. row
		SearchRow searchRow = s.makeRow();
		ArrayList<QnaDTO> ar = null;
		int totalCount = 0;
		try {
			ar = qnadao.list(searchRow);
			//2. page
			totalCount = qnadao.getTotalCount(searchRow);
			SearchPager searchPager = s.makePage(totalCount);
			
			request.setAttribute("pager", searchPager);
			request.setAttribute("list", ar);
			actionforward.setCheck(true);
			actionforward.setPath("../WEB-INF/views/qna/qnaList.jsp");
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
		// TODO Auto-generated method stub
		return null;
	}
	
	//write
	@Override
	public actionForward insert(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		actionforward.setCheck(true);
		actionforward.setPath("../WEB-INF/views/qna/qnaWrite.jsp");
		
		String method = request.getMethod();
		if(method.equals("POST")) {
			String saveDirectory = request.getServletContext().getRealPath("upload");
			int maxPostSize = 1024*1024*100;
			Connection con = null;
			try {
				MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", new DefaultFileRenamePolicy());
				Enumeration<String> e = multipartRequest.getFileNames();
				ArrayList<UploadDTO> ar = new ArrayList<UploadDTO>();
				while(e.hasMoreElements()) {
					String s =e.nextElement();
					String fname = multipartRequest.getFilesystemName(s);
					String oname = multipartRequest.getOriginalFileName(s);
					UploadDTO uploadDTO = new UploadDTO();
					uploadDTO.setFname(fname);
					uploadDTO.setOname(oname);
					ar.add(uploadDTO);
				} //파일 name속성이 불분명하기 때문에 모두 받아와서 세팅
				QnaDTO qnaDTO = new QnaDTO();
				qnaDTO.setTitle(multipartRequest.getParameter("title"));
				qnaDTO.setWriter(multipartRequest.getParameter("writer"));
				qnaDTO.setContents(multipartRequest.getParameter("contents"));
				
				//1. 시퀀스 번호
				int num = qnadao.getNum();
				qnaDTO.setNum(num);
				con = DBConnector.getConnect();
				con.setAutoCommit(false);
				//2. qna insert
				num = qnadao.insert(qnaDTO, con);
				
				//3. upload insert
				for(UploadDTO uploadDTO : ar) {
					uploadDTO.setNum(qnaDTO.getNum());
					num = uploadDAO.insert(uploadDTO, con);
					if(num<1) {
						throw new Exception();
					}
				}
				
				con.commit();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			}
			actionforward.setCheck(false);
			actionforward.setPath("./qnaList");
			
		}//post끝
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
