package com.bookaholic.demo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.bookaholic.demo.util.JwtUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Order(1)
@WebFilter(filterName = "authenticationFilter", urlPatterns = "/*" , initParams = {
        @WebInitParam(name = "URL", value = "http://localhost:8080")})
public class AuthenticationFilter implements Filter {
	@Autowired
	private JwtUtils jwtUtils;
	
    private String url;
    /**
     * execute only once
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.url = filterConfig.getInitParameter("URL");
    }

    /**
     * main code
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	Map<String, String> map = new HashMap<>();
    	String url = ((HttpServletRequest)servletRequest).getRequestURI();
    	if(url != null) {
    		System.out.println("url: " + url);
    		// pass if it is login or signup
    		if("/common/login".equals(url) || "/common/signup".equals(url)) {
    			filterChain.doFilter(servletRequest, servletResponse);
    			return;
    		} else if ("/common/tokenlogin".equals(url)) {
				System.out.println("login with token");
				String token = ((HttpServletRequest)servletRequest).getHeader("token");
				Map<String, Object> userInfoMap = jwtUtils.tokenLogin(token);
				if (userInfoMap == null) {
					map.put("errorMsg", "token login failed");
				} else {
					//Convert map to json string
		    		GsonBuilder builder = new GsonBuilder();
		    		builder.setPrettyPrinting();
		    		Gson gson = builder.create();
		    		String jsonString = gson.toJson(userInfoMap);
		    		servletResponse.setContentType("application/json");
		    		servletResponse.setCharacterEncoding("utf-8");
		    		((HttpServletResponse)servletResponse).setStatus(200);
		    		
		    		// Response
		    		PrintWriter pw = servletResponse.getWriter();
		    		pw.write(jsonString);
		    		pw.flush();
		    		pw.close();
				}		
			}
    		else {
    			System.out.println(url + " needs authentication");
    			String token = ((HttpServletRequest)servletRequest).getHeader("token");
    			if(token != null && token.length() != 0) {
    				int result = jwtUtils.verify(token);
    				if (result == 1) {
    	    			filterChain.doFilter(servletRequest, servletResponse);
    	    			return;
    				} else if (result == 2)
    					map.put("errorMsg", "token has expired");
    				else if (result == 0)
    					map.put("errorMsg", "authentication failed");
    			} else {
    				map.put("errorMsg", "token required");
    			}
    		}
    		
    		//Convert map to json string
    		GsonBuilder builder = new GsonBuilder();
    		builder.setPrettyPrinting();
    		Gson gson = builder.create();
    		String jsonString = gson.toJson(map);
    		servletResponse.setContentType("application/json");
    		servletResponse.setCharacterEncoding("utf-8");
    		((HttpServletResponse)servletResponse).setStatus(403);
    		
    		// Response
    		PrintWriter pw = servletResponse.getWriter();
    		pw.write(jsonString);
    		pw.flush();
    		pw.close();
    	}
    }

    /**
     * call it when destroy 
     */
    @Override
    public void destroy() {
        System.out.println("Bye...");
    }
}