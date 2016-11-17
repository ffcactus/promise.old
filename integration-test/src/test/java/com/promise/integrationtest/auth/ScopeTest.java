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

import com.promise.auth.sdk.AccessPoint;
import com.promise.auth.sdk.dto.CreateScopeRequest;
import com.promise.auth.sdk.dto.CreateScopeResponse;
import com.promise.auth.sdk.dto.GetScopeListResponse;
import com.promise.auth.sdk.dto.GetScopeResponse;
import com.promise.integrationtest.util.CommonTestUtil;
import com.promise.integrationtest.util.HttpJsonClient;

public class ScopeTest
{
    private static final String URI_HEAD = "http://139.129.234.210/rest/scope";
    private static final CreateScopeRequest createRequest0;
    private static final CreateScopeRequest createRequest1;
    private static final List<AccessPoint> accessPointList0;
    private static final List<AccessPoint> accessPointList1;

    static
    {
        createRequest0 = new CreateScopeRequest();
        createRequest0.setName("Admin Scope");
        createRequest0.setDescription("Admin scope that has all the rights.");
        accessPointList0 = new ArrayList<>();
        accessPointList0.add(new AccessPoint(AccessPoint.URI, "rest/auth"));
        accessPointList0.add(new AccessPoint(AccessPoint.URI, "rest/task"));
        accessPointList0.add(new AccessPoint(AccessPoint.URI, "rest/scope"));
        createRequest0.setAccessPointList(accessPointList0);

        createRequest1 = new CreateScopeRequest();
        createRequest1.setName("User Scope");
        createRequest1.setDescription("User scope that doesn't have all the rights.");
        accessPointList1 = new ArrayList<>();
        accessPointList1.add(new AccessPoint(AccessPoint.URI, "rest/auth"));
        accessPointList1.add(new AccessPoint(AccessPoint.URI, "rest/task"));
        createRequest1.setAccessPointList(accessPointList1);
    }

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
    public void testCreateScope()
    {
        final ResponseEntity<CreateScopeResponse> postRet = HttpJsonClient
                .httpPost(URI_HEAD, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postRet.getBody();
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postRet.getStatusCodeValue());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(createRequest0.getName(), postResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse.getDescription());
        Assert.assertNotNull(postResponse.getId());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse.getAccessPointList()));

        // Clean up.
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId());
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testDeleteExistScope()
    {
        final ResponseEntity<CreateScopeResponse> postRet = HttpJsonClient
                .httpPost(URI_HEAD, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postRet.getBody();
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postRet.getStatusCodeValue());
        Assert.assertEquals(createRequest0.getName(), postResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse.getDescription());
        Assert.assertNotNull(postResponse.getId());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse.getAccessPointList()));

        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId());
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testDeleteNoneExistScope()
    {
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/xxxx");
        Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testGetScope()
    {
        final ResponseEntity<CreateScopeResponse> postScopeRet = HttpJsonClient
                .httpPost(URI_HEAD, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse = postScopeRet.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postScopeRet.getStatusCodeValue());
        Assert.assertEquals(createRequest0.getName(), postResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse.getDescription());
        Assert.assertNotNull(postResponse.getId());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse.getAccessPointList()));

        final ResponseEntity<GetScopeResponse> getScopeRet = HttpJsonClient
                .httpGet(URI_HEAD + "/" + postResponse.getId(), GetScopeResponse.class);
        final GetScopeResponse getResponse = getScopeRet.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_OK, getScopeRet.getStatusCodeValue());
        CommonTestUtil.assertPromiseResource(postResponse);
        Assert.assertEquals(createRequest0.getName(), getResponse.getName());
        Assert.assertEquals(createRequest0.getDescription(), getResponse.getDescription());
        Assert.assertNotNull(getResponse.getId());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), getResponse.getAccessPointList()));

        // Clean up.
        final ResponseEntity<String> deleteRet = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse.getId());
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet.getStatusCodeValue());
    }

    @Test
    public void testGetScopeList()
    {
        final ResponseEntity<CreateScopeResponse> postScopeRet0 = HttpJsonClient
                .httpPost(URI_HEAD, createRequest0, CreateScopeResponse.class);
        final CreateScopeResponse postResponse0 = postScopeRet0.getBody();

        final ResponseEntity<CreateScopeResponse> postScopeRet1 = HttpJsonClient
                .httpPost(URI_HEAD, createRequest1, CreateScopeResponse.class);
        final CreateScopeResponse postResponse1 = postScopeRet1.getBody();

        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postScopeRet0.getStatusCodeValue());
        Assert.assertEquals(createRequest0.getName(), postResponse0.getName());
        Assert.assertEquals(createRequest0.getDescription(), postResponse0.getDescription());
        Assert.assertNotNull(postResponse0.getId());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), postResponse0.getAccessPointList()));

        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, postScopeRet1.getStatusCodeValue());
        Assert.assertEquals(createRequest1.getName(), postResponse1.getName());
        Assert.assertEquals(createRequest1.getDescription(), postResponse1.getDescription());
        Assert.assertNotNull(postResponse1.getId());
        Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest1.getAccessPointList(), postResponse1.getAccessPointList()));

        final ResponseEntity<GetScopeListResponse> getScopeListRet = HttpJsonClient
                .httpGet(URI_HEAD, GetScopeListResponse.class);
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
            Assert.assertNotNull(t0.getId());
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), t0.getAccessPointList()));

            final GetScopeResponse t1 = getScopeListResponse.getMemberList().get(1);
            CommonTestUtil.assertPromiseResource(t1);
            Assert.assertEquals(createRequest1.getName(), t1.getName());
            Assert.assertEquals(createRequest1.getDescription(), t1.getDescription());
            Assert.assertNotNull(t1.getId());
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest1.getAccessPointList(), t1.getAccessPointList()));
        }
        else
        {
            final GetScopeResponse t0 = getScopeListResponse.getMemberList().get(0);
            CommonTestUtil.assertPromiseResource(t0);
            Assert.assertEquals(createRequest1.getName(), t0.getName());
            Assert.assertEquals(createRequest1.getDescription(), t0.getDescription());
            Assert.assertNotNull(t0.getId());
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest1.getAccessPointList(), t0.getAccessPointList()));

            final GetScopeResponse t1 = getScopeListResponse.getMemberList().get(1);
            CommonTestUtil.assertPromiseResource(t1);
            Assert.assertEquals(createRequest0.getName(), t1.getName());
            Assert.assertEquals(createRequest0.getDescription(), t1.getDescription());
            Assert.assertNotNull(t1.getId());
            Assert.assertTrue(CommonTestUtil.collectionEquals(createRequest0.getAccessPointList(), t1.getAccessPointList()));
        }

        // Clean up.
        final ResponseEntity<String> deleteRet0 = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse0.getId());
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet0.getStatusCodeValue());

        final ResponseEntity<String> deleteRet1 = HttpJsonClient
                .httpDelete(URI_HEAD + "/" + postResponse1.getId());
        Assert.assertEquals(HttpURLConnection.HTTP_ACCEPTED, deleteRet1.getStatusCodeValue());
    }

}
