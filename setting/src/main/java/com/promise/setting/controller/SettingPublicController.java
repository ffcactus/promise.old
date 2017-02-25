package com.promise.setting.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.promise.common.PromiseErrorResponse;
import com.promise.common.constant.PromiseCategory;
import com.promise.common.exception.InternelErrorException;
import com.promise.common.exception.InvalidRequestBodyException;
import com.promise.common.exception.PromiseException;
import com.promise.setting.sdk.dto.UploadUpgradeFileResponse;

@RestController
@RequestMapping("/rest")
public class SettingPublicController
{
    private final Logger log = Logger.getLogger(SettingPublicController.class);

    /**
     * The exception handler for this controller.
     *
     * @param req The Servlet Request
     * @param ex The exception
     * @return The HTTP response that represents the exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<PromiseErrorResponse> exceptionHandler(HttpServletRequest req, Exception ex)
    {
        final PromiseErrorResponse response;

        if (ex instanceof PromiseException)
        {
            log.info("Handling PromiseException " + ((PromiseException) ex).getMessage());
            response = PromiseErrorResponse.makeInstance((PromiseException) ex);

        }
        else if (ex.getCause() instanceof PromiseException)
        {
            log.info("Handling PromiseException " + ((PromiseException) ex.getCause()).getMessage());
            response = PromiseErrorResponse.makeInstance((PromiseException) ex.getCause());
        }
        else
        {
            log.info("Handling unknown Exception " + ex.getStackTrace());
            response = PromiseErrorResponse.makeInstance(new InternelErrorException(PromiseCategory.TASK));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Post a task.
     *
     * @param header The header of the HTTP request.
     * @param request The HTTP request.
     * @return The HTTP response that represents the task created.
     * @throws Exception
     */
    //@PromisePublicInterface
    @RequestMapping(value = "/setting/upgrade/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadUpgradeFileResponse> uploadUpgradeFile(
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
        return null;
    }
}
