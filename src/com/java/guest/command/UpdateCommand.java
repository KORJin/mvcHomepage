package com.java.guest.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;
import com.java.logger.MyLooger;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num=Integer.parseInt(request.getParameter("num"));
		MyLooger.logger.info(MyLooger.logMsg + num);
		
		GuestDto guestDto=GuestDao.getInstance().upSelect(num);
		MyLooger.logger.info(MyLooger.logMsg + guestDto.toString());
		
		request.setAttribute("guestDto", guestDto);
		
		return "/WEB-INF/views/guest/update.jsp";
	}

}
