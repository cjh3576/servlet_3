package com.jh.point;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jh.action.Action;
import com.jh.action.actionForward;

public class pointService implements Action{
	private pointDAO pointdao;
	public pointService() {
		pointdao = new pointDAO();
	}
	
	public actionForward selectList(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		
		return actionforward;
	}
	
	public actionForward selectOne(HttpServletRequest request, HttpServletResponse response) {
		actionForward actionforward = new actionForward();
		
		return actionforward;
	}
}
