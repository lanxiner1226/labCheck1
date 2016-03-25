/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.checkIn.security;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.hdu.lab.checkIn.security.exceptions.AuthorizationException;

/**
 * 拦截器
 * @author justin
 */
public class SecurityInterceptor
    implements HandlerInterceptor{
    
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}    

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// excluded URLs:
		// see http://stackoverflow.com/questions/9908124/spring-mvc-3-interceptor-on-all-excluding-some-defined-paths
		String requestUri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}
        
		// intercept
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			// see http://stackoverflow.com/questions/12713873/spring-3-1-how-do-you-send-all-exception-to-one-page
			throw new AuthorizationException("Unauthorized Access Denied");
		} else{
			return true;
		}
    }

    @Override
	public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
    }

    @Override
	public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }
    
}
