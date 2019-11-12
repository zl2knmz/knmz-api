package com.knmz.exception;

import com.knmz.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * JerseyExceptionHandler
 *
 * @author zl
 * @date 2019/8/1 14:24
 */
@Provider
public class JerseyExceptionHandler implements ExceptionMapper<Exception> {

    private final static Logger LOGGER = LoggerFactory.getLogger(JerseyExceptionHandler.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error(e.getMessage(), e);
        ApiResult ret = ApiResult.init4Fail();
        if (e instanceof ServiceException) {
            ret.fail(100, ((ServiceException) e).getErrorMessage());
        } else {
            ret.fail(500, "Internal Server Error");
        }
        return Response.ok(ret, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
