package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.logger.MyLooger;
import com.java.member.dao.MemberDao;

public class IdCheckCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id=request.getParameter("id");
		MyLooger.logger.info(MyLooger.logMsg + id);
		
		// 해당 id 있으면 1, 해당 id 없으면 0
		int check=MemberDao.getInstance().idCheck(id);
		MyLooger.logger.info(MyLooger.logMsg + check);
		
		request.setAttribute("id", id);
		request.setAttribute("check", check);
				
		return "/WEB-INF/views/member/idCheck.jsp";
	}

}
