package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.logger.MyLooger;
import com.java.member.dao.MemberDao;

public class DeleteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		
		String password=request.getParameter("password");
		MyLooger.logger.info(MyLooger.logMsg + id + "," + password);
		
		int check=MemberDao.getInstance().delete(id, password);
		MyLooger.logger.info(MyLooger.logMsg +check);
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/member/deleteOk.jsp";
	}

}
