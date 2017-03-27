package com.promise.setting.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.promise.common.PromiseExceptionController;
import com.promise.common.exception.InvalidRequestBodyException;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class SettingPublicController extends PromiseExceptionController
{

    /**
     * Upload upgrade file.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the task created.
     * @throws Exception
     */
    //@PromisePublicInterface
    @RequestMapping(value = "/setting/upgrade/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadUpgradeFile(
            @RequestHeader Map<String, String> header,
            @RequestParam(name = "file") MultipartFile file)
            throws InvalidRequestBodyException
    {
        try
        {
            final File dest = new File("/tmp/" + file.getOriginalFilename());
            Files.copy(file.getInputStream(), dest.toPath());
            //            final String tempFileName = "/tmp/" + file.getOriginalFilename();
            //            final FileOutputStream fo = new FileOutputStream(tempFileName);
            //            final InputStream inputStream = file.getInputStream();
            //            final File dest = new File("/tmp/" + file.getOriginalFilename());
            //            file.transferTo(dest);
            //            fo.close();
            //}
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
        //        final HttpHeaders responseHeaders = new HttpHeaders();
        //        responseHeaders.setAccessControlAllowOrigin("*");
        //        final List<HttpMethod> methodList = new ArrayList<>();
        //        methodList.add(HttpMethod.POST);
        //        responseHeaders.setAccessControlAllowMethods(methodList);
        //        final List<String> requestHeaders = new ArrayList<>();
        //        requestHeaders.add("Origin");
        //        requestHeaders.add("Content-Type");
        //        requestHeaders.add("X-Auth-Token");
        //        responseHeaders.setAccessControlRequestHeaders(requestHeaders);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
