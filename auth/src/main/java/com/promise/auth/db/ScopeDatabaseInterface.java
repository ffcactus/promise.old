package com.promise.auth.db;

public interface ScopeDatabaseInterface {

	public ScopeDao createScope(ScopeDao scopeDao);

	public ScopeDao getScope(String id);

	public void deleteScope(String id);

	public void updateScope(String id, ScopeDao scopeDao);

	public AccessPointDao createAccessPoint(AccessPointDao accessPoint);

	public void removeAccessPoint(String id);
	
	public void bindAccessPointToScope(String accessPointId, String scopeId);
	
	public void unbindAccessPointFromScope(String accessPointId, String scopeId);
	
}
