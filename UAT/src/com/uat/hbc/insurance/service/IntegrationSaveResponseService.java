package com.uat.hbc.insurance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uat.hbc.common.dao.IntegrationSaveResponseDao;
import com.uat.hbc.common.model.Result;



@Service
public class IntegrationSaveResponseService {	

	@Autowired
	private IntegrationSaveResponseDao integrationSaveResponseDao;
	
	public Result insertUpdateIntgRecordPKGwithArray(Integer imGrpId, Integer gicId, String sessionId,
			Integer responseType, HashMap<String, String> map, String tcpIpAdd, String userId, String branchId,
			String userDesc, String procedureName) {
		ArrayList<String> keyList = new ArrayList<>();
		ArrayList<String> valList = new ArrayList<>();
		
		Set outResult = map.entrySet();
		Iterator<Map.Entry<String, String>> resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry<String, String> entry = resItr.next();
			keyList.add(entry.getKey());
			valList.add(entry.getValue());
		}
		
//		System.out.println("imGrpId----Integer------------>>"+imGrpId);
//		System.out.println("gicId------Integer---------->>"+gicId);
//		System.out.println("sessionId---------------->>"+sessionId);
//		System.out.println("responseType--Integer-------------->>"+responseType);
//		System.out.println("tcpIpAdd---------------->>"+tcpIpAdd);
//		System.out.println("userId---------------->>"+userId);
//		System.out.println("branchId---------------->>"+branchId);
//		System.out.println("userDesc---------------->>"+userDesc);
//		System.out.println("procedureName---------------->>"+procedureName);
		
		return integrationSaveResponseDao.insertUpdateIntgRecordPKGwithArray(imGrpId, gicId, sessionId, responseType, keyList.toArray(new String[map.size()]), valList.toArray(new String[map.size()]), tcpIpAdd, userId, branchId, userDesc, procedureName);
	}
}
