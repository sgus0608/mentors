package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckLoginInterceptor {
	private static CheckLoginInterceptor instance=new CheckLoginInterceptor();
	private ArrayList<String> permitAllList=new ArrayList<>();
	private CheckLoginInterceptor() {
		permitAllList.add("FreeBoardFindPostListController"); 
		permitAllList.add("MentoringBoardFindPostListController");
		permitAllList.add("QnABoardFindPostListController");
		permitAllList.add("RegisterMemberController");
		permitAllList.add("TipsBoardFindPostListController");
		permitAllList.add("LoginController");
		permitAllList.add("CheckIdController");
	}
	public static CheckLoginInterceptor getInstance() {
		return instance;
	}
	public boolean checkLogin(HttpServletRequest request,String controllerName) {
		boolean result=true;
		if(permitAllList.contains(controllerName)==false) {
			HttpSession session=request.getSession(false);
			if(session==null||session.getAttribute("mvo")==null) {
				result=false;
			}
		}
		return result;
	}
}
