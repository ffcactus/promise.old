package com.promise.auth.db;


public class ScopeDatabaseImpl implements ScopeDatabaseInterface {

	@Override
	public ScopeDao createScope(ScopeDao scopeDao) {
		return scopeDao;		
	}

	@Override
	public ScopeDao getScope(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteScope(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateScope(String id, ScopeDao scopeDao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccessPointDao createAccessPoint(AccessPointDao accessPoint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccessPoint(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindAccessPointToScope(String accessPointId, String scopeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbindAccessPointFromScope(String accessPointId, String scopeId) {
		// TODO Auto-generated method stub
		
	}

}
