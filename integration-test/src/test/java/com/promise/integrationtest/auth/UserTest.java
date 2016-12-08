package com.promise.integrationtest.auth;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.CreateUserRequest;
import com.promise.auth.sdk.dto.CreateUserResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseToken;
import com.promise.integrationtest.util.CommonTestUtil;
import com.promise.integrationtest.util.HttpJsonClient;

public class UserTest
{
    private static final String hostname = "http://192.168.116.130";
    private static final String URI_HEAD = hostname + "/rest/user";
    private static final CreateScopeRequest createScopeRequest;
    private static String scopeUri;
    private static PromiseToken token;

    static
    {
        createScopeRequest = new CreateScopeRequest();
        createScopeRequest.setName("Admin Scope");
        createScopeRequest.setDescription("Admin scope that has all the rights.");
        final List<PromiseAccessPoint> accessPointList = new ArrayList<>();
        accessPointList.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/auth"));
        accessPointList.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/task"));
        accessPointList.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/scope"));
        createScopeRequest.setAccessPointList(accessPointList);
    }

    @BeforeClass
    public static void setUpBeforeClass()
            throws Exception
    {
        final PostLoginRequest request = new PostLoginRequest();
        request.setUserName("Administrator");
        request.setPassword("admin");
        request.setDomain("local");
        final ResponseEntity<PostLoginResponse> response = HttpJsonClient.httpPost(
                hostname + "/rest/login",
                null,
                request,
                PostLoginResponse.class);
        if (response.getStatusCode() == HttpStatus.OK)
        {
            token = new PromiseToken(response.getBody().getToken());
        }
        else
        {
            throw new Exception("Failed to login.");
        }

        // Create scopes.
        final ResponseEntity<CreateScopeResponse> createScopeResponse = HttpJsonClient
                .httpPost(hostname + "/rest/scope", token, createScopeRequest, CreateScopeResponse.class);
        Assert.assertEquals(HttpStatus.CREATED, createScopeResponse.getStatusCode());
        scopeUri = createScopeResponse.getBody().getUri();
    }

    @AfterClass
    public static void tearDownAfterClass()
            throws Exception
    {
        // Clean up.
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(hostname + scopeUri, token);
        Assert.assertEquals(HttpStatus.ACCEPTED, deleteRet.getStatusCode());
    }

    @Before
    public void setUp()
            throws Exception
    {
    }

    @After
    public void tearDown()
            throws Exception
    {
    }

    @Test
    public void testCreateUser()
    {
        final CreateUserRequest createUserRequest = new CreateUserRequest();
        final List<String> scopeUriList = new ArrayList<>();
        scopeUriList.add(scopeUri);
        createUserRequest.setUsername("baibin");
        createUserRequest.setEmail("baibin@email.com");
        createUserRequest.setPassword("iforgot".toCharArray());
        createUserRequest.setScopeUri(scopeUriList);

        final ResponseEntity<CreateUserResponse> postRet = HttpJsonClient
                .httpPost(URI_HEAD, token, createUserRequest, CreateUserResponse.class);
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postRet.getStatusCodeValue());
        final CreateUserResponse postResponse = postRet.getBody();
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(createUserRequest.getUsername(), postResponse.getUsername());
        Assert.assertEquals(createUserRequest.getEmail(), postResponse.getEmail());

        // Clean up.
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId(), token);
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }
}
