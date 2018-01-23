package com.zwj.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	/**
	 * Jwt 验证过滤,先从http请求中获取token(cookies,parameter,header),再从token中获取用户名.
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//System.out.println("filter --------------------------");
		String authToken = request.getHeader(this.tokenHeader);
		if (null == authToken)
			authToken = request.getParameter("token");
		if (null == authToken)
			authToken = getCookie(request, "token");
		//System.out.println("token: "+authToken+"----------------------------");
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		System.out.println("name:" + username + "----------------------------");
		/**
		 * 1.先从数据库中查询是否存在该用户
		 * 2.验证用户名与token中的用户是否一致
		 * 3.
		 */
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

	String getCookie(HttpServletRequest httpRequest, String name) {
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : httpRequest.getCookies()) {
				if (name.equalsIgnoreCase(cookie.getName()))
					return cookie.getValue();
			}
		}
		return null;
	}
}