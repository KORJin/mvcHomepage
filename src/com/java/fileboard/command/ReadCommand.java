package com.java.fileboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileboard.dao.BoardDao;
import com.java.fileboard.dto.BoardDto;
import com.java.command.Command;
import com.java.logger.MyLooger;

public class ReadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
			int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			MyLooger.logger.info(MyLooger.logMsg + boardNumber + "," + pageNumber);
			
			BoardDto boardDto=BoardDao.getInstance().read(boardNumber);
			MyLooger.logger.info(MyLooger.logMsg + boardDto.toString());
			
			if(boardDto.getFileName() !=null) {
				int index=boardDto.getFileName().indexOf('_') + 1;
				boardDto.setFileName(boardDto.getFileName().substring(index));
			}
			
			request.setAttribute("boardDto", boardDto);
			request.setAttribute("pageNumber", pageNumber);
			
		return "/WEB-INF/views/fileboard/read.jsp";
	}

}
