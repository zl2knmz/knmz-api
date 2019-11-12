package com.knmz.controller;

import com.knmz.constant.KnmzConstants;
import com.knmz.exception.ServiceException;
import com.knmz.model.ApiResult;
import com.knmz.service.UserService;
import com.knmz.utils.CodeGenerator;
import com.knmz.utils.DESUtils;
import com.knmz.utils.ZxingUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.misc.BASE64Decoder;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 *
 * @author zl
 * @date 2019/7/23 18:57
 */
@Path("/user")
@Produces("application/json;charset=utf-8")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GET
    @Path("/checkstatus")
    public Response checkStatus() {
        return Response.ok(true).build();
    }

    @POST
    @Path("/login")
    public Response login(@FormParam("phoneOrEmail") String phoneOrEmail, @FormParam("password") String password) {
        ApiResult ret = ApiResult.init4Fail();
        ret.ok(userService.login(phoneOrEmail, password));
        return Response.ok(ret).build();
    }

    @GET
    @Path("/info")
    public Response info() {
        ApiResult ret = ApiResult.init4Fail();
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        ret.ok(userService.getUserInfo(account));
        return Response.ok(ret).build();
    }

    @GET
    @Path("/login_token")
    public Response loginToken() {
        ApiResult ret = ApiResult.init4Fail();
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        ret.ok(DESUtils.encrypt4dotnet(String.format("%s:%s", account, CodeGenerator.generate(0, CodeGenerator.BaseCode.SECONDS))));
        return Response.ok(ret).build();
    }

    @GET
    @Path("/invitation_code")
    public Response invitationCode() {
        ApiResult ret = ApiResult.init4Fail();
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        String invitationCode = DESUtils.encrypt4dotnet(account);
        if (StringUtils.isNotBlank(invitationCode)) {
            ret.ok(invitationCode);
        } else {
            ret.fail("please try again later.");
        }
        return Response.ok(ret).build();
    }

    @POST
    @Path("/scan_qr")
    public Response scanQrcode(@FormParam("imgBase64") String imgBase64) {
        ApiResult ret = ApiResult.init4Fail();
        try {
            if (imgBase64.indexOf("data:image/png;base64,") > -1) {
                imgBase64 = imgBase64.replace("data:image/png;base64,", "");
            } else if (imgBase64.indexOf("data:image/jpg;base64,") > -1) {
                imgBase64 = imgBase64.replace("data:image/jpg;base64,", "");
            } else if (imgBase64.indexOf("data:image/jpeg;base64,") > -1) {
                imgBase64 = imgBase64.replace("data:image/jpeg;base64,", "");
            } else if (imgBase64.indexOf("data:image/gif;base64,") > -1) {
                imgBase64 = imgBase64.replace("data:image/gif;base64,", "");
            } else if (imgBase64.indexOf("data:image/tiff;base64,") > -1) {
                imgBase64 = imgBase64.replace("data:image/tiff;base64,", "");
            }
            BASE64Decoder d = new BASE64Decoder();
            byte[] data = d.decodeBuffer(imgBase64);
            String content = ZxingUtils.readQRCode(data);
            Map<String, Object> result = new HashMap<>();
            if (content.startsWith("http") || content.startsWith("https")) {
                result.put("url", content);
            } else {
                if (content.startsWith("ethereum:")) {
                    result.put("cointype", KnmzConstants.COIT_TYPE.ETH);
                    result.put("address", content.replace("ethereum:", ""));
                } else if (content.startsWith("0x0")) {
                    result.put("cointype", KnmzConstants.COIT_TYPE.ETH);
                    result.put("address", content);
                } else if (content.startsWith("1") || content.startsWith("3")) {
                    result.put("cointype", KnmzConstants.COIT_TYPE.BTC);
                    result.put("address", content);
                }
            }
            ret.ok(result);
        } catch (ServiceException ex) {
            ret.fail(-1, ex.getErrorMessage());

        } catch (IOException ex) {
            logger.error("scanQrcode error", ex);
            ret.fail("識別二維碼錯誤");
        }

        return Response.ok(ret).build();
    }

}