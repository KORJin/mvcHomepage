package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.logger.MyLooger;

public class RegisterCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MyLooger.logger.info(MyLooger.logMsg);
			
		return "/WEB-INF/views/member/register.jsp";
	}

}
