package com.bpn.SpringBootThymeleaf.lib;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class CustomizedErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

    Map<String, Object> errorAttribute = super.getErrorAttributes(webRequest, includeStackTrace);
    errorAttribute.put("parameters", webRequest.getParameterMap());
    return errorAttribute;
  }
}
