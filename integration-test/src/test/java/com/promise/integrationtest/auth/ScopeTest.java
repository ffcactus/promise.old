package com.promise.integrationtest.auth;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.auth.dto.AccessPoint;
import com.promise.auth.dto.CreateScopeRequest;

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

    @Test
    public void testScopeCreation()
            throws Exception
    {
        final CreateScopeRequest request = new CreateScopeRequest();
        request.setName("Admin Scope");
        request.setDescription("Admin scope that have all the right.");
        final List<AccessPoint> accessPointList = new ArrayList<AccessPoint>();
        accessPointList.add(new AccessPoint(AccessPoint.URI, "rest/auth"));
        accessPointList.add(new AccessPoint(AccessPoint.URI, "rest/task"));
        request.setAccessPointList(accessPointList);
        //this.restTemplate.postForObject("http://localhost:" + port + "/rest/scope", request, CreateScopeResponse.class);
        final URL url = new URL(URI_HEAD);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(1000);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        final OutputStream os = connection.getOutputStream();
        os.write(new ObjectMapper().writeValueAsBytes(request));
        os.flush();
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());

    }

}
