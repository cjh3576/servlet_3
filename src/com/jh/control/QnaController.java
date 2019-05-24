package com.jh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jh.action.actionForward;
import com.jh.qna.QnaService;

/**
 * Servlet implementation class QnaController
 */
@WebServlet("/QnaController")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaService qnaService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaController() {
        super();
        qnaService = new QnaService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();
		actionForward actionforward = null;
		if(command.equals("/qnaList")) {
			actionforward = qnaService.list(request, response);
		}else if(command.equals("/qnaWrite")){
			actionforward = qnaService.insert(request, response);
		}else {
			
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
