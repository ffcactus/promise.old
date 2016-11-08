package com.promise.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.auth.db.AccessPointDao;
import com.promise.auth.db.ScopeDao;
import com.promise.auth.db.ScopeDatabaseInterface;
import com.promise.auth.dto.AccessPointDto;
import com.promise.auth.dto.CreateScopeRequestDto;
import com.promise.auth.dto.GetScopeResponseDto;

@Component
@Scope("singleton")
public class ScopeServiceImpl implements ScopeServiceInterface {

	@Autowired
	private ScopeDatabaseInterface db;
	
	@Override
	public void createScope(CreateScopeRequestDto dto) {
		ScopeDao scopeDao = new ScopeDao();
		scopeDao.setName(dto.getName());
		String scopeId = db.createScope(scopeDao).getId();
		ArrayList<String> accessPointIdList = new ArrayList<String>();
		for(AccessPointDto each : dto.getAccessPointList()) {
			accessPointIdList.add(db.createAccessPoint(convertAccessPointDto(each)).getId());
		}
		for(String each : accessPointIdList) {
			db.bindAccessPointToScope(each, scopeId);
		}		
	}

	@Override
	public GetScopeResponseDto getScope(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateScope(String id, CreateScopeRequestDto dto) {
		// TODO Auto-generated method stub

	}

	private AccessPointDao convertAccessPointDto(AccessPointDto input) {
		AccessPointDao ret = new AccessPointDao();
		ret.setType(input.getType());
		ret.setValue(input.getValue());
		return ret;
	}
}
