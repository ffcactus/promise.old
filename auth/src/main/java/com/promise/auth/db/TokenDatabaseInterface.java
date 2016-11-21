package com.promise.auth.db;

import com.promise.common.PromiseToken;
import com.promise.common.PromiseUser;

public interface TokenDatabaseInterface {

	PromiseToken makeAndCacheToken(PromiseUser user);
}
