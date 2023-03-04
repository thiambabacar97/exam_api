package sn.isi.security;

import com.mourchidtech.examjeedevops.security.properties.JwtEnvironment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtEnvironment jwtEnvironment;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With,"+
				"Content-Type, Access-Control-Request-Method,"
				+ " Access-Control-RequestHeaders,authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,"+
				"Access-Control-Allow-Credentials, authorization");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,PATCH,HEAD");
        String jwt = request.getHeader(SecurityConstant.HEADER_STRING);

        if(request.getMethod().equals("OPTIONS")) {
        	response.setStatus(HttpServletResponse.SC_OK);
        }else {

	        if(jwt == null || !jwt.startsWith(SecurityConstant.TOKEN_PREFIX)){
	            filterChain.doFilter(request, response);
	            return;
	        }

	        Claims claims = Jwts.parser()
	                .setSigningKey(SecurityConstant.getSecret())//
	                .parseClaimsJws(jwt.replace(SecurityConstant.TOKEN_PREFIX, ""))
	                .getBody();
	        String username = claims.getSubject();
	        ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>)claims.get("roles");
	        Collection<GrantedAuthority> authorities = new ArrayList<>();

	        roles.forEach(r -> {
	            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
	        });

	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                new UsernamePasswordAuthenticationToken(username, null, authorities);
	        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        filterChain.doFilter(request, response);
	  }
	}


}
