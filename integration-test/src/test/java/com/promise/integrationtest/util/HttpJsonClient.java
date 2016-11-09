package com.promise.integrationtest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpJsonClient
{
    public static <T, E> ResponseEntity<T> httpPost(String url, E request, Class<T> responseClass)
    {
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("POST");
            c.setConnectTimeout(1000);
            c.setReadTimeout(1000);
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestMethod("POST");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            final OutputStream os = c.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(request));
            os.flush();
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

    public static <T> ResponseEntity<T> httpGet(String url, Class<T> responseClass)
    {
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("POST");
            c.setConnectTimeout(1000);
            c.setReadTimeout(1000);
            c.setDoInput(true);
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-Type", "application/json");
            c.setRequestProperty("Accept", "application/json");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
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
}
