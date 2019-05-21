package com.jh.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//List
	public abstract actionForward list(HttpServletRequest request, HttpServletResponse response);
	//select
	public abstract actionForward select(HttpServletRequest request, HttpServletResponse response);
	
	//insert
	public abstract actionForward insert(HttpServletRequest request, HttpServletResponse response);

	//update
	public abstract actionForward update(HttpServletRequest request, HttpServletResponse response);
	
	//delete
	public abstract actionForward delete(HttpServletRequest request, HttpServletResponse response);
	
}
