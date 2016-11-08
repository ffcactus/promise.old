package com.promise.auth.db;

import com.promise.common.exception.NoDBInstanceException;

public interface ScopeDatabaseInterface {

	public ScopeDao createScope(ScopeDao scopeDao);

	public ScopeDao getScope(String id)
	        throws NoDBInstanceException;

	public void deleteScope(String id)
	        throws NoDBInstanceException;

	public ScopeDao updateScope(String id, ScopeDao scopeDao)
	        throws NoDBInstanceException;

	public AccessPointDao createAccessPoint(AccessPointDao accessPoint);

	public void removeAccessPoint(String id)
	        throws NoDBInstanceException;

	public void bindAccessPointToScope(String accessPointId, String scopeId);

	public void unbindAccessPointFromScope(String accessPointId, String scopeId);

}
