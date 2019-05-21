package com.jh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jh.action.actionForward;
import com.jh.notice.noticeService;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private noticeService noticeservice;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        noticeservice = new noticeService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();
		actionForward actionforward = null;
		if(command.equals("/noticeList")){
			actionforward= noticeservice.list(request, response);
		}else if(command.equals("/noticeSelect")) {
			actionforward= noticeservice.select(request, response);
		}else if(command.equals("/noticeUpdate")) {
			actionforward= noticeservice.update(request, response);
		}else if(command.equals("/noticeWrite")) {
			actionforward= noticeservice.insert(request, response);
		}
		
		
		
		
		if(actionforward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionforward.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(actionforward.getPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
