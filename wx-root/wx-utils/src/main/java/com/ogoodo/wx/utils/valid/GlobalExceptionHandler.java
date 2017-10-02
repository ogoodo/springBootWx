package com.ogoodo.wx.utils.valid;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;  

import org.springframework.validation.FieldError;  
import org.springframework.web.bind.MethodArgumentNotValidException;  
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 参考: 各种异常都能捕获到处理 http://www.cnblogs.com/softidea/p/4949620.html
 * @description:全局异常处理
 * @author chen
 */

@ControllerAdvice
@ResponseBody  
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value=MethodArgumentNotValidException.class)  
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,  
    MethodArgumentNotValidException exception) throws Exception  
    {
        logger.info("参数验证失败", exception);
        //按需重新封装需要返回的错误信息  
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();  
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息  
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {  
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();  
            invalidArgument.setDefaultMessage(error.getDefaultMessage());  
            invalidArgument.setField(error.getField());  
            invalidArgument.setRejectedValue(error.getRejectedValue());  
            invalidArguments.add(invalidArgument);  
        }

        return new AjaxResult().failure(10004, "参数不符合规范", invalidArguments);

        //        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.PARAMETER_ERROR.getErrcode(), ResultStatusCode.PARAMETER_ERROR.getErrmsg(), invalidArguments);  
        //        return resultMsg;

        // Map<String,Object> map=new HashMap<String,Object>();
        // //        map.put("a", ResultStatusCode.PARAMETER_ERROR.getErrcode());
        // //        map.put("b",  ResultStatusCode.PARAMETER_ERROR.getErrmsg());
        // map.put("c", invalidArguments);
        // return map;
    }

//    /**
//     * 500 - Internal Server Error
//    */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(ServiceException.class)
//    public AjaxResult handleServiceException(ServiceException e) {
//        logger.error("业务逻辑异常", e);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("list", "listvalue");
//        map.put("test", "testvalue");
//        return new AjaxResult().failure(10004, "业务逻辑异常：" + e.getMessage(), map);
//        //        Map<String,Object> map=new HashMap<String,Object>();
//        //        map.put("err", e.getMessage());
//        //        return map;
//    }




/**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public AjaxResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    logger.error("缺少请求参数", e);
    return new AjaxResult().failure("required_parameter_is_not_present");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    logger.error("参数解析失败", e);
    return new AjaxResult().failure("could_not_read_json");
  }

//  /**
//   * 400 - Bad Request
//   */
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//    logger.error("参数验证失败", e);
//    BindingResult result = e.getBindingResult();
//    FieldError error = result.getFieldError();
//    String field = error.getField();
//    String code = error.getDefaultMessage();
//    String message = String.format("%s:%s", field, code);
//    return new AjaxResult().failure(message);
//  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public AjaxResult handleBindException(BindException e) {
    logger.error("参数绑定失败", e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    return new AjaxResult().failure("(BindException.class)" + message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public AjaxResult handleServiceException(ConstraintViolationException e) {
    logger.error("参数验证失败", e);
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String message = violation.getMessage();
    return new AjaxResult().failure("parameter(ConstraintViolationException.class):" + message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public AjaxResult handleValidationException(ValidationException e) {
    logger.error("参数验证失败", e);
    return new AjaxResult().failure("validation_exception(ValidationException.class)");
  }

  /**
   * 405 - Method Not Allowed
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public AjaxResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    logger.error("不支持当前请求方法", e);
    return new AjaxResult().failure("request_method_not_supported(HttpRequestMethodNotSupportedException.class)");
  }

  /**
   * 415 - Unsupported Media Type
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public AjaxResult handleHttpMediaTypeNotSupportedException(Exception e) {
    logger.error("不支持当前媒体类型", e);
    return new AjaxResult().failure("content_type_not_supported(HttpMediaTypeNotSupportedException.class)");
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServiceException.class)
  public AjaxResult handleServiceException(ServiceException e) {
    logger.error("业务逻辑异常", e);
    return new AjaxResult().failure("自定义业务逻辑异常(ServiceException.class)：" + e.getMessage());
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public AjaxResult handleException(Exception e) {
    logger.error("通用异常", e);
    return new AjaxResult().failure("通用异常(Exception.class)：" + e.getMessage());
  }

  /**
   * 操作数据库出现异常:名称重复，外键关联
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public AjaxResult handleException(DataIntegrityViolationException e) {
    logger.error("操作数据库出现异常:", e);
    return new AjaxResult().failure("操作数据库出现异常(DataIntegrityViolationException.class)：字段重复、有外键关联等");
  }

}

