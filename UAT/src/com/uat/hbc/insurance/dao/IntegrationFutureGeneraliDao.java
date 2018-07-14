package com.uat.hbc.insurance.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.uat.hbc.common.Utils;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.commonFactory.DbProcessImpl;

@Repository
public class IntegrationFutureGeneraliDao {

	private DbProcessImpl dbProcessImpl;

	public DbProcessImpl getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(DbProcessImpl dbProcessImpl) {
		this.dbProcessImpl = dbProcessImpl;
	}

	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;

	ArrayList<String> resultList = new ArrayList<String>();
	JSONArray array = new JSONArray();
	String retMsg = "";
	String jsonNames1 = "";
	String jsonNames = "";
	
	public String getFutureGData(String pkg_name, String proc_name,HashMap inputParaList) {
		
		HashMap cst = dbProcess.executeProcedure(pkg_name, proc_name,
				inputParaList);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("po_error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
			}
			if (getKey.equalsIgnoreCase("po_msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
			}
			if (getKey.equalsIgnoreCase("Poc_Sql_Result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
				System.out.println("jsonNamesPremiumrequest----" + jsonNames);
			}
			if (getKey.equalsIgnoreCase("Poc_Covers")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames1 = gson.toJson(list);
				System.out.println("covers----" + jsonNames1);
			}
		}
		System.out.println("jsonNames1.isEmpty()-->>"+jsonNames1.isEmpty());
		System.out.println("jsonNames1.length()-->>"+jsonNames1.length());
		
		String s=jsonNames1.replace("[{", "").replace("}]", "");
		System.out.println("s.length()---->>"+s.length());
		if (s.length()>0) {
			jsonNames = jsonNames + jsonNames1;
//			if (jsonNames.contains("}][{")) {
//				jsonNames = jsonNames.replace("}][{", " ,");
//			}
			System.out.println("New JSON Response With Covers In Dao ::: " +jsonNames);
			
		}
	
		return jsonNames;
	}

	public boolean getShah256(String passToHash) {
		 String hex = "";
		 String fileHash="";
		 try{
				Properties props = null;
				try {
					props = Utils.readProperties("datasource.properties");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				String sourceFileName = props.getProperty("server.path")+"/hash/shaString.txt";
				
				File file = new File(sourceFileName);
				if(file.exists()){
					file.delete();
				}
				file.createNewFile();
				
				MessageDigest sha256 = MessageDigest.getInstance("SHA256");
		        FileInputStream fis = new FileInputStream(file);
		  
		        byte[] data = new byte[1024];
		        int read = 0; 
		        while ((read = fis.read(data)) != -1) {
		            sha256.update(data, 0, read);
		        };
		        byte[] hashBytes = sha256.digest();
		  
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < hashBytes.length; i++) {
		          sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
		        }
		         
		        fileHash = sb.toString();
		        
		 } catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return fileHash.equals(passToHash);
		 
	}
	
}
