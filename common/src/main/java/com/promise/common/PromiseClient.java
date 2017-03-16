package com.promise.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.common.exception.PromiseException;

public class PromiseClient
{
    public static final int CONNECTION_TIMEOUT = 20 * 1000;
    public static final int READ_TIMEOUT = 20 * 1000;
    public final static String URL_HEAD = "http://localhost";
    public static final String LOCAL_TOKEN_FILE = "/tmp/promise_local_token";

    /**
     * Get the module token
     *
     * @param ModuleName The name of the module.
     * @return The module token, or null if any error.
     */
    public static PromiseToken getModuleToken(String ModuleName)
    {
        final Path file = Paths.get(LOCAL_TOKEN_FILE);
        try
        {
            final List<String> content = Files.readAllLines(file, StandardCharsets.UTF_8);
            if ((content != null) && (content.size() > 0))
            {
                return new PromiseToken(content.get(0));
            }
            else
            {
                return null;
            }
        }
        catch (final IOException e)
        {
            return null;
        }
    }

    public static Map<String, String> makeHeader(PromiseToken token, PromiseAccessPoint accessPoint)
    {
        final Map<String, String> ret = new HashMap<>();
        if (token != null)
        {
            ret.put("promise-token", token.getValue());
        }
        if (accessPoint != null)
        {
            ret.put("promise-accesspoint-type", accessPoint.getType());
            ret.put("promise-accesspoint-value", accessPoint.getValue());
        }

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

    /**
     * When you are sure the response always is a fixed class, use this one.
     * Or use httpPost.
     *
     * @param url
     * @param request
     * @param header
     * @param responseClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, E> ResponseEntity<T> httpPostAndConvert(
            String url,
            E request,
            Map<String, String> header,
            Class<T> responseClass)
    {
        return (ResponseEntity<T>) httpPost(url, request, header, responseClass);
    }

    /**
     * Common HTTP POST operation.
     *
     * @param url The URL of the operation.
     * @param request The request.
     * @param header The header of the request.
     * @param responseClass The class of the response.
     * @return The response of the operation.
     */
    public static <T, E> ResponseEntity<?> httpPost(String url, E request, Map<String, String> header, Class<?> responseClass)
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

            try
            {
                c.connect();
            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR));
            }
            int status;
            try
            {
                status = c.getResponseCode();
            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.valueOf(HttpURLConnection.HTTP_INTERNAL_ERROR));
            }

            final BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            br.close();
            final ObjectMapper mapper = new ObjectMapper();
            switch (status)
            {
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_CREATED:
                    return new ResponseEntity<>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    return new ResponseEntity<>(
                            mapper.readValue(sb.toString(), PromiseException.class),
                            HttpStatus.valueOf(status));
                default:
                    // TODO
                    return new ResponseEntity<>(null, HttpStatus.valueOf(status));
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

    /**
     * Common HTTP PUT operation.
     *
     * @param url The URL of the operation.
     * @param request The request.
     * @param header The header of the request.
     * @param responseClass The class of the response.
     * @return The response of the operation.
     */
    public static <T, E> ResponseEntity<T> httpPut(String url, E request, Map<String, String> header, Class<T> responseClass)
    {
        HttpURLConnection c = null;
        try
        {
            final URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("PUT");
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
                    return new ResponseEntity<>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
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

    /**
     * The common HTTP GET operation.
     *
     * @param url The URL of the operation.
     * @param header The header of the request.
     * @param responseClass The class of response
     * @return The response of the operation.
     */
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
                    return new ResponseEntity<>(mapper.readValue(sb.toString(), responseClass), HttpStatus.valueOf(status));
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return new ResponseEntity<>((T) null, HttpStatus.valueOf(status));
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

    /**
     * The common HTTP DELETE operation.
     *
     * @param url The URL of the operation.
     * @param header THe Header of the request.
     * @return The response of the operation.
     */
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
                    return new ResponseEntity<>(sb.toString(), HttpStatus.valueOf(status));
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return new ResponseEntity<>((String) null, HttpStatus.valueOf(status));
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
