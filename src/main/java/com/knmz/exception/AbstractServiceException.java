package com.knmz.exception;

/**
 * 异常规范
 *
 * @author zl
 * @date 2019/11/12 11:02
 */
public interface AbstractServiceException {
    /**
     * 获取异常的状态码
     *
     * @return int
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     *
     * @return string
     */
    String getMessage();
}
