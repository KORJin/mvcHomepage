package com.java.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;
import com.java.command.Command;
import com.java.logger.MyLooger;

public class ListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber == null) pageNumber="1";
		
		int currengPage=Integer.parseInt(pageNumber);
		MyLooger.logger.info(MyLooger.logMsg + currengPage);
		
		// 한페이지당 게시물 1page 10개 / start 1, end 10
		int boardSize=10;
		int startRow=(currengPage-1) * boardSize +1;
		int endRow=currengPage*boardSize;
		
		int count=BoardDao.getInstance().getCount();
		MyLooger.logger.info(MyLooger.logMsg + count);	
		
		ArrayList<BoardDto> boardList=null;
		if(count > 0) {
			boardList=BoardDao.getInstance().boardList(startRow, endRow);
			MyLooger.logger.info(MyLooger.logMsg + boardList.size());
		}
		
		request.setAttribute("boardSize", boardSize);         	// 한페이지당 게시물 수
		request.setAttribute("currengPage", currengPage);  	// 요청페이지
		request.setAttribute("boardList", boardList);     			// 게시물 리스트
		request.setAttribute("count", count);              			// 전체 게시물 수 
		
		return "/WEB-INF/views/board/list.jsp";
	}

}







