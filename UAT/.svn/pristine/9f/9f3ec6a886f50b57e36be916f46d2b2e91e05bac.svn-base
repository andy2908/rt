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
public class KotakDaoIntegration {

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
	String jsonNames = "";
	String coverjsonnames = "";
//	 String path="classpath:dataSource.properties";

	public String getKotakData(String pkg_name, String proc_name, HashMap inputParaList) {

		HashMap cst = dbProcess.executeProcedure(pkg_name, proc_name, inputParaList);
		System.out.println("cst---->>>>>" + cst);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Po_Error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("POC_SQL_RESULT")) {
				List list = (List) me.getValue();
				System.out.println("POC_SQL_RESULT list-->>>" + list);
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
				System.out.println("jsonNames Kotak Dao------>>>>" + jsonNames);
			}
			if (getKey.equalsIgnoreCase("POC_COVERS")) {
				List list = (List) me.getValue();
				System.out.println("list-->>>" + list);
				Gson gson = new Gson();
				coverjsonnames = gson.toJson(list);
				System.out.println("coverjsonnames Kotak Dao------>>>>" + coverjsonnames);
			}
			jsonNames = jsonNames + coverjsonnames;
			// jsonNames = jsonNames;

			// System.out.println("jsonNames +
			// coverjsonnames;-->>>>"+jsonNames);
		}
		System.out.println("jsonNames + coverjsonnames;-->>>>" + jsonNames);
		// if (jsonNames.contains("}][{")) {
		// jsonNames = jsonNames.replace("}][{", ",");
		// }
		// if (jsonNames.contains("[") || jsonNames.contains("]") ) {
		// jsonNames = jsonNames.replace("[", "");
		// jsonNames = jsonNames.replace("]", "");
		// }
		if (jsonNames.contains("PYP_TYPE")) {
			jsonNames = jsonNames.replace("PYP_TYPE", "PreviousPolicyTypeOrTypeofCover");
		}

		System.out.println("New JSON-->>>>" + jsonNames);

		return jsonNames;
	}

	public String getRecordList(String sqlMstId, String param, String flag, String workingDate, String branchMstId,
			String deptMstId, String entityId, String userId, String primaryKey) {
		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		String jsonnames = "";
		String jsonNames1 = "";
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("Pi_Sql_Mst_Id", sqlMstId);
		inputParaList.put("Pi_Sql_Param", param);
		inputParaList.put("Pi_OpFlag", flag);
		inputParaList.put("Pi_OP_DATE", workingDate);
		inputParaList.put("Pi_BRANCH_ID", branchMstId);
		inputParaList.put("Pi_USER_ID", userId);
		inputParaList.put("Pi_ENTER_DESC", null);
		inputParaList.put("Pio_PK", primaryKey);
		inputParaList.put("Pi_USER_DESC", null);

		HashMap<String, Object> cst = dbProcess.executeProcedure("", "PR_CALL_SQL", inputParaList);
		System.out.println("Head cst==> " + cst);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Po_OpId")) {

			}
			if (getKey.equalsIgnoreCase("Pio_PK")) {
			}
			if (getKey.equalsIgnoreCase("po_error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("retMsg" + retMsg);
			}
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("retMsg" + retMsg);
			}
			if (getKey.equalsIgnoreCase("poc_sql_result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonnames = gson.toJson(list);
			}
			System.out.println(jsonnames);
		}
		return jsonnames;
	}

	// public String get_SHA_512_SecurePassword(String passwordToHash) {
	//
	// MessageDigest md;
	// try {
	// md = MessageDigest.getInstance("SHA-512");
	//
	// byte[] dataBytes = new byte[1024];
	// int nread = 0;
	// while ((!passwordToHash.toString().equalsIgnoreCase(""))) {
	// md.update(dataBytes);
	// }
	// ;
	// byte[] mdbytes = md.digest();
	// // convert the byte to hex format method
	// StringBuffer sb = new StringBuffer();
	// for (int i = 0; i < mdbytes.length; i++) {
	// sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100,
	// 16).substring(1));
	// }
	// System.out.println("Hex format : " + sb.toString());
	// // convert the byte to hex format method 2
	//// StringBuffer hexString = new StringBuffer();
	//// for (int i = 0; i < mdbytes.length; i++) {
	//// hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
	//// }
	//// System.out.println("Hex format : " + hexString.toString());
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return passwordToHash;
	// }

	public String getSha512() {

		MessageDigest md;
		StringBuffer sb = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		
			Properties props = null;
			try {
				props = Utils.readProperties("datasource.properties");
			} catch (IOException e1) {
				e1.printStackTrace();
			} 

			String sourceFileName = props.getProperty("server.path")+"/hash/shaString.txt";
			
//			File file = new File(sourceFileName);
//			if(file.exists()){
//				file.delete();
//			}
//			file.createNewFile();
			
				FileInputStream fis = new FileInputStream(sourceFileName);
				byte[] dataBytes = new byte[1024];
				int nread = 0;
				while ((nread = fis.read(dataBytes)) != -1)
				{
					md.update(dataBytes, 0, nread);
				};
				byte[] mdbytes = md.digest();
				//convert the byte to hex format method
				sb = new StringBuffer();
				for (int i = 0; i< mdbytes.length; i++) 
				{
					sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100,16).substring(1));
				}
				System.out.println("Hex format : " + sb.toString());
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
				return sb.toString();
	}
	
//	public String getSha512Decrypt(){
//		
//	}

}
