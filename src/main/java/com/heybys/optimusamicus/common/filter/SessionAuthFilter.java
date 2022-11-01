package com.heybys.optimusamicus.common.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.PatternMatchUtils;

@Slf4j
// @WebFilter(filterName = "SessionAuthFilter")
public class SessionAuthFilter implements Filter {

  private static final Map<HttpMethod, String[]> whitelist = new HashMap<>();

  public void init(FilterConfig config) throws ServletException {
    whitelist.put(HttpMethod.GET, new String[] {});
    whitelist.put(
        HttpMethod.POST,
        new String[] {"/api/v1/auth/login", "/api/v1/auth/logout", "/api/v1/users"});
    whitelist.put(HttpMethod.PUT, new String[] {});
    whitelist.put(HttpMethod.PATCH, new String[] {});
    whitelist.put(HttpMethod.DELETE, new String[] {});
  }

  public void destroy() {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String requestURI = httpRequest.getRequestURI();
    String method = httpRequest.getMethod();
    log.info("requestURI : [{}] {}", method, requestURI);

    if (isLoginRequired(HttpMethod.valueOf(method), requestURI)) {
      HttpSession session = httpRequest.getSession(false);
      if (session == null) {
        // httpResponse.sendRedirect("/api/v1/auth/login?redirectURL=" + requestURI);
        throw new RuntimeException();
      } else {
        Enumeration<String> attributeNames = session.getAttributeNames();
        for (String attributeName : Collections.list(attributeNames)) {
          Object attribute = session.getAttribute(attributeName);
          log.info("{} : {}", attributeName, attribute);
        }
      }
    }

    chain.doFilter(request, response);
  }

  private boolean isLoginRequired(HttpMethod method, String requestURI) {
    return !PatternMatchUtils.simpleMatch(whitelist.get(method), requestURI);
  }
}