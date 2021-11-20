package com.java.guest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.guest.dao.GuestDao;
import com.java.logger.MyLooger;

public class DeleteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num=Integer.parseInt(request.getParameter("num"));
		MyLooger.logger.info(MyLooger.logMsg + num);
		
		int check=GuestDao.getInstance().delete(num);
		MyLooger.logger.info(MyLooger.logMsg + check);
		
		request.setAttribute("check", check);
		return "/WEB-INF/views/guest/delete.jsp";
	}
}
