package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.logger.MyLooger;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;

public class RegisterOkCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto=new MemberDto();
		memberDto.setId(request.getParameter("id"));
		memberDto.setPassword(request.getParameter("password"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setJumin1(request.getParameter("jumin1"));
		memberDto.setJumin2(request.getParameter("jumin2"));
		
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress(request.getParameter("address"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
		
		memberDto.setInterest(request.getParameter("interest"));
		
		if(memberDto.getName().equals("관리자")) {
			memberDto.setMemberLevel("MA");
		}else {
			memberDto.setMemberLevel("BA");
		}
		MyLooger.logger.info(MyLooger.logMsg + memberDto.toString());
		
		int check=MemberDao.getInstance().insert(memberDto);
		MyLooger.logger.info(MyLooger.logMsg + check);
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/member/registerOk.jsp";

	}

}








