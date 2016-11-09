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
import org.springframework.http.ResponseEntity;

import com.promise.auth.dto.AccessPoint;
import com.promise.auth.dto.CreateScopeRequest;
import com.promise.auth.dto.CreateScopeResponse;
import com.promise.integrationtest.util.HttpJsonClient;

public class ScopeTest
{
    private static final String URI_HEAD = "http://localhost:8000/rest/scope";

    @BeforeClass
    public static void setUpBeforeClass()
            throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass()
            throws Exception
    {
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

    /**
     * Test the POST GET DELETE of scope.
     *
     * @throws Exception
     */
    @Test
    public void testScopeCreation()
            throws Exception
    {
        // Create the scope.
        final CreateScopeRequest request = new CreateScopeRequest();
        request.setName("Admin Scope");
        request.setDescription("Admin scope that have all the right.");
        final List<AccessPoint> accessPointList = new ArrayList<AccessPoint>();
        accessPointList.add(new AccessPoint(AccessPoint.URI, "rest/auth"));
        accessPointList.add(new AccessPoint(AccessPoint.URI, "rest/task"));
        request.setAccessPointList(accessPointList);

        // Test the creation result.
        final ResponseEntity<CreateScopeResponse> postRet = HttpJsonClient.httpPost(URI_HEAD, request, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postRet.getBody();
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postRet.getStatusCodeValue());
        Assert.assertEquals(request.getName(), postResponse.getName());
        Assert.assertEquals(request.getDescription(), postResponse.getDescription());
        Assert.assertNotNull(postResponse.getId());
        Assert.assertArrayEquals(request.getAccessPointList().toArray(), postResponse.getAccessPointList().toArray());

        // Test the get result.
        final ResponseEntity<CreateScopeResponse> getRet = HttpJsonClient
                .httpGet(URI_HEAD + "/" + postResponse.getId(), CreateScopeResponse.class);
        final CreateScopeResponse getResponse = getRet.getBody();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, getRet.getStatusCodeValue());
        Assert.assertEquals(request.getName(), getResponse.getName());
        Assert.assertEquals(request.getDescription(), getResponse.getDescription());
        Assert.assertNotNull(getResponse.getId());
        Assert.assertArrayEquals(request.getAccessPointList().toArray(), getResponse.getAccessPointList().toArray());

        // Delete the scope.

        // Check the result of non-existence.
    }

}
