package com.java.fileboard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.fileboard.dto.BoardDto;
import com.java.command.Command;
import com.java.logger.MyLooger;

public class WriteOkBeforeCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {		
		// 보관클래스
		DiskFileItemFactory factory=new DiskFileItemFactory();
		
		// 업로드할 클래스 
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		// 업로드에 request 
		List<FileItem> list=upload.parseRequest(request);
		Iterator<FileItem> iter=list.iterator();
		
		BoardDto boardDto=new BoardDto();
		
		while(iter.hasNext()) {
			FileItem fileItem=iter.next();
			
			if(fileItem.isFormField()) {   			// txt
				if(fileItem.getFieldName().equals("boardNumber")) {
					boardDto.setBoardNumber(Integer.parseInt(fileItem.getString()));
				}
				
				if(fileItem.getFieldName().equals("groupNumber")) {
					boardDto.setGroupNumber(Integer.parseInt(fileItem.getString()));
				}
				
				if(fileItem.getFieldName().equals("sequenceNumber")) {
					boardDto.setSequenceNumber(Integer.parseInt(fileItem.getString()));
				}
				
				if(fileItem.getFieldName().equals("sequenceLevel")) {
					boardDto.setSequenceLevel(Integer.parseInt(fileItem.getString()));
				}
				
				if(fileItem.getFieldName().equals("writer")) {
					boardDto.setWriter(fileItem.getString("utf-8"));
				}
				
				if(fileItem.getFieldName().equals("subject")) {
					boardDto.setSubject(fileItem.getString("utf-8"));
				}
				
				if(fileItem.getFieldName().equals("email")) {
					boardDto.setEmail(fileItem.getString("utf-8"));
				}
				
				if(fileItem.getFieldName().equals("content")) {
					boardDto.setContent(fileItem.getString("utf-8"));
				}
				
				if(fileItem.getFieldName().equals("password")) {
					boardDto.setPassword(fileItem.getString("utf-8"));
				}
				
			}else {    // binary 
				if(fileItem.getFieldName().equals("file")) {
					String fileName=fileItem.getName();					// 파일명
					if(fileName==null || fileName.equals(""))  continue;
					String timeFileName=Long.toString(System.currentTimeMillis()) + "-" + fileName;
					
					upload.setFileSizeMax(1024*1024*10);              // 1024 * 100
					long size=fileItem.getSize();							// 파일사이즈
					
					String dir="C:\\Users\\MinHye\\Desktop\\teacher\\JSP\\workspace\\mvcHomepage\\WebContent\\pds";		// 절대경로
					MyLooger.logger.info(MyLooger.logMsg + fileName + "," + size + "," + dir);
					
					File file=new File(dir, timeFileName);
					
					BufferedInputStream fis=null;
					BufferedOutputStream fos=null;
					
					try {
						fis=new BufferedInputStream(fileItem.getInputStream());
						fos=new BufferedOutputStream(new FileOutputStream(file));
						
						while(true) {
							int data=fis.read();
							if(data == -1) break;
							fos.write(data);
						}
						
						fos.flush();
						
					}catch(Exception e) {
						e.printStackTrace();
					}finally {
						if(fis !=null) fis.close();
						if(fos !=null) fos.close();
					}
					
					boardDto.setFileName(timeFileName);
					boardDto.setPath(file.getAbsolutePath());
					boardDto.setFileSize(size);
					
				}
			}
		}
		
		MyLooger.logger.info(MyLooger.logMsg + boardDto.toString());
		
		
		// return "/WEB-INF/views/fileboard/writeOk.jsp";
		return null;
	}
}




























