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
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.auth.sdk.dto.PostLoginRequest;
import com.promise.auth.sdk.dto.PostLoginResponse;
import com.promise.common.PromiseAccessPoint;
import com.promise.common.PromiseToken;
import com.promise.integrationtest.util.CommonTestUtil;
import com.promise.integrationtest.util.HttpJsonClient;

public class ScopeTest
{
    private static final String hostname = "http://192.168.116.130";
    private static final String URI_HEAD = hostname + "/rest/scope";
    private static final CreateScopeRequest createRequest0;
    private static final CreateScopeRequest createRequest1;
    private static final List<PromiseAccessPoint> accessPointList0;
    private static final List<PromiseAccessPoint> accessPointList1;
    private static PromiseToken token;

    static
    {
        createRequest0 = new CreateScopeRequest();
        createRequest0.setName("Admin Scope");
        createRequest0.setDescription("Admin scope that has all the rights.");
        accessPointList0 = new ArrayList<>();
        accessPointList0.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/auth"));
        accessPointList0.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/task"));
        accessPointList0.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/scope"));
        createRequest0.setAccessPointList(accessPointList0);

        createRequest1 = new CreateScopeRequest();
        createRequest1.setName("User Scope");
        createRequest1.setDescription("User scope that doesn't have all the rights.");
        accessPointList1 = new ArrayList<>();
        accessPointList1.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/auth"));
        accessPointList1.add(new PromiseAccessPoint(PromiseAccessPoint.URI, "rest/task"));
        createRequest1.setAccessPointList(accessPointList1);
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
    public void testCreateScope()
    {
        final ResponseEntity<CreateScopeResponse> postRet = HttpJsonClient
                .httpPost(URI_HEAD, token, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postRet.getBody();
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postRet.getStatusCodeValue());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(createRequest0.getName(), postResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse.getDescription());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse.getAccessPointList()));

        // Clean up.
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId(), token);
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testDeleteExistScope()
    {
        final ResponseEntity<CreateScopeResponse> postRet = HttpJsonClient
                .httpPost(URI_HEAD, token, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postRet.getBody();
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postRet.getStatusCodeValue());
        Assert.assertEquals(createRequest0.getName(), postResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse.getDescription());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse.getAccessPointList()));

        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId(), token);
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testDeleteNoneExistScope()
    {
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/xxxx", token);
        Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testGetScope()
    {
        final ResponseEntity<CreateScopeResponse> postScopeRet = HttpJsonClient
                .httpPost(URI_HEAD, token, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postScopeRet.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postScopeRet.getStatusCodeValue());
        Assert.assertEquals(createRequest0.getName(), postResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse.getDescription());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse.getAccessPointList()));

        final ResponseEntity<GetScopeResponse> getScopeRet = HttpJsonClient
                .httpGet(URI_HEAD + "/" + postResponse.getId(), token, GetScopeResponse.class);
        final GetScopeResponse getResponse = getScopeRet.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_OK, getScopeRet.getStatusCodeValue());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(createRequest0.getName(), getResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), getResponse.getDescription());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), getResponse.getAccessPointList()));

        // Clean up.
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId(), token);
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testGetScopeList()
    {
        final ResponseEntity<CreateScopeResponse> postScopeRet0 = HttpJsonClient
                .httpPost(URI_HEAD, token, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse0 = postScopeRet0.getBody();

        final ResponseEntity<CreateScopeResponse> postScopeRet1 = HttpJsonClient
                .httpPost(URI_HEAD, token, createRequest1, CreateScopeResponse.class);
        final CreateScopeResponse postResponse1 = postScopeRet1.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postScopeRet0.getStatusCodeValue());
        Assert.assertEquals(createRequest0.getName(), postResponse0.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse0.getDescription());
        CommonTestUtil.assertPromiseResource(postResponse0);
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse0.getAccessPointList()));

        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postScopeRet1.getStatusCodeValue());
        Assert.assertEquals(createRequest1.getName(), postResponse1.getName());
        Assert.assertEquals(createRequest1.getDescription(), postResponse1.getDescription());
        CommonTestUtil.assertPromiseResource(postResponse1);
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest1.getAccessPointList(), postResponse1.getAccessPointList()));

        final ResponseEntity<GetScopeListResponse> getScopeListRet = HttpJsonClient
                .httpGet(URI_HEAD, token, GetScopeListResponse.class);
        final GetScopeListResponse getScopeListResponse = getScopeListRet.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_OK, getScopeListRet.getStatusCodeValue());
        Assert.assertEquals(0, getScopeListResponse.getStart());
        Assert.assertEquals(2, getScopeListResponse.getCount());
        Assert.assertEquals(2, getScopeListResponse.getMemberList().size());

        if (getScopeListResponse.getMemberList().get(0).getId().equals(postResponse0.getId()))
        {
            final GetScopeResponse t0 = getScopeListResponse.getMemberList().get(0);
            CommonTestUtil.assertPromiseResource(t0);
            Assert.assertEquals(createRequest0.getName(), t0.getName());
            Assert.assertEquals(createRequest0.getDescription(), t0.getDescription());
            CommonTestUtil.assertPromiseResource(t0);
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), t0.getAccessPointList()));

            final GetScopeResponse t1 = getScopeListResponse.getMemberList().get(1);
            CommonTestUtil.assertPromiseResource(t1);
            Assert.assertEquals(createRequest1.getName(), t1.getName());
            Assert.assertEquals(createRequest1.getDescription(), t1.getDescription());
            CommonTestUtil.assertPromiseResource(t1);
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest1.getAccessPointList(), t1.getAccessPointList()));
        }
        else
        {
            final GetScopeResponse t0 = getScopeListResponse.getMemberList().get(0);
            CommonTestUtil.assertPromiseResource(t0);
            Assert.assertEquals(createRequest1.getName(), t0.getName());
            Assert.assertEquals(createRequest1.getDescription(), t0.getDescription());
            CommonTestUtil.assertPromiseResource(t0);
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest1.getAccessPointList(), t0.getAccessPointList()));

            final GetScopeResponse t1 = getScopeListResponse.getMemberList().get(1);
            CommonTestUtil.assertPromiseResource(t1);
            Assert.assertEquals(createRequest0.getName(), t1.getName());
            Assert.assertEquals(createRequest0.getDescription(), t1.getDescription());
            CommonTestUtil.assertPromiseResource(t1);
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), t1.getAccessPointList()));
        }

        // Clean up.
        final ResponseEntity<String> deleteRet0 = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse0.getId(), token);
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet0.getStatusCodeValue());

        final ResponseEntity<String> deleteRet1 = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse1.getId(), token);
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet1.getStatusCodeValue());
    }

}
