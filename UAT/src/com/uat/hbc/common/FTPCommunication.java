package com.uat.hbc.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPCommunication {
	
	public String sendFiletoFTP(byte[] byteArr,String fileName,String[] folderName,String link, String userName,String password){
		int port = 21;
		FTPClient ftpClient = new FTPClient();
		String status ="";
		boolean done = false;
		
        try {
        	ftpClient.connect(link, port);
        	System.out.println("userName::"+userName);
        	System.out.println("password::"+password);
        	
        	done = ftpClient.login(userName, password);
        	System.out.println("logged in?::"+done);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			InputStream inputStream = new ByteArrayInputStream(byteArr);
			String str = "";
			for(int i=0;i<folderName.length;i++){
				str = str+"/"+folderName[i];
				if(!ftpClient.changeWorkingDirectory(str)){
					done = ftpClient.makeDirectory(str);
					System.out.println("folder created?::"+done);
				}
			}
			ftpClient.changeWorkingDirectory(str);
			
			done = ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			
			if(done){
				status = "success";
			}else{
				status = "failed";
			}
			System.out.println("folder created?::"+status);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return status;
	}
	
	public byte[] readFilefromFTP(String link,String filePath, String userName,String password,String[] folderName){
		int port = 21;
		FTPClient ftpClient = new FTPClient();
		boolean done = false;
		try {
        	ftpClient.connect(link, port);
			ftpClient.login(userName, password);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			String str = "";
			for(int i=0;i<folderName.length;i++){
				str = str+"/"+folderName[i];
				if(!ftpClient.changeWorkingDirectory(str)){
//					done = ftpClient.makeDirectory(str);
					done=false;
					System.out.println("folder created?::"+done);
				}
			}
			done = ftpClient.changeWorkingDirectory(str);
			System.out.println("str ::::"+ftpClient.printWorkingDirectory());
			System.out.println("str ::::"+str);
			
			if(done){
				String[] files = ftpClient.listNames();
				
				System.out.println("str ::::"+files);
				System.out.println("str ::::"+files.length);
				if (files != null && files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						System.out.println("list ::::"+files[i]);
					}
				}
				done = ftpClient.retrieveFile(filePath, out);
			}
			
			if(done){
				return out.toByteArray();
			}else{
				return new byte[0];
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
