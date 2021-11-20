package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.logger.MyLooger;
import com.java.member.dao.MemberDao;

public class LoginOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		MyLooger.logger.info(MyLooger.logMsg + id + "," + password);
		
		String memberLevel=MemberDao.getInstance().loginCheck(id, password);
		MyLooger.logger.info(MyLooger.logMsg + memberLevel);
		
		request.setAttribute("memberLevel", memberLevel);
		request.setAttribute("id", id);
		
		return "/WEB-INF/views/member/loginOk.jsp";
	}

}
