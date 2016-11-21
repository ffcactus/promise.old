package com.promise.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PromiseClient
{
    public static final int CONNECTION_TIMEOUT = 1000;
    public static final int READ_TIMEOUT = 1000;

    public static Map<String, String> makeHeader(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        final Map<String, String> ret = new HashMap<String, String>();
        ret.put("promise-token", token.getValue());
        ret.put("promise-accesspoint-type", accessPoint.getType());
        ret.put("promise-accesspoint-value", accessPoint.getValue());
        return ret;
    }

    public static PromiseToken getToken(Map<String, String> header)
    {

        final String value = header.get("promise-token");
        if (value != null)
        {
            return new PromiseToken(value);
        }
        else
        {
            return null;
        }
    }

    public static PromiseAccessPoint getAccessPoint(Map<String, String> header)
    {
        final String type = header.get("promise-accesspoint-type");
        final String value = header.get("promise-accesspoint-value");
        if (type != null && value != null)
        {
            return new PromiseAccessPoint(type, value);
        }
        else
        {
            return null;
        }
    }

    public static <T, E> ResponseEntity<T> httpPost(String url, E request, Map<String, String> header, Class<T> responseClass)
    {
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("POST");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            if (header != null)
            {
                for (final String key : header.keySet())
                {
                    c.setRequestProperty(key, header.get(key));
                }
            }
            final OutputStream os = c.getOutputStream();
            if (request != null)
            {
                os.write(new ObjectMapper().writeValueAsBytes(request));
                os.flush();
            }

            c.connect();
            final int status = c.getResponseCode();
            switch (status)
            {
                case 200:
                case HttpURLConnection.HTTP_CREATED:
                    final BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    final StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    br.close();
                    final ObjectMapper mapper = new ObjectMapper();
                    return new ResponseEntity<T>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
            }
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public static <T> ResponseEntity<T> httpGet(String url, Map<String, String> header, Class<T> responseClass)
    {
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            if (header != null)
            {
                for (final String key : header.keySet())
                {
                    c.setRequestProperty(key, header.get(key));
                }
            }
            c.connect();
            final int status = c.getResponseCode();
            switch (status)
            {
                case HttpURLConnection.HTTP_OK:
                    final BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    final StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    br.close();
                    final ObjectMapper mapper = new ObjectMapper();
                    return new ResponseEntity<T>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return new ResponseEntity<T>((T) null, HttpStatus.valueOf(status));
            }
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }

    public static ResponseEntity<String> httpDelete(String url, Map<String, String> header)
    {
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("DELETE");
            c.setConnectTimeout(CONNECTION_TIMEOUT);
            c.setReadTimeout(READ_TIMEOUT);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            if (header != null)
            {
                for (final String key : header.keySet())
                {
                    c.setRequestProperty(key, header.get(key));
                }
            }
            c.connect();
            final int status = c.getResponseCode();
            switch (status)
            {
                case HttpURLConnection.HTTP_ACCEPTED:
                    final BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    final StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return new ResponseEntity<String>(sb.toString(), HttpStatus.valueOf(status));
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return new ResponseEntity<String>((String) null, HttpStatus.valueOf(status));
            }
        }
        catch (final MalformedURLException ex)
        {
            System.out.println(ex);
        }
        catch (final IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            if (c != null)
            {
                try
                {
                    c.disconnect();
                }
                catch (final Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }
        return null;
    }
}
