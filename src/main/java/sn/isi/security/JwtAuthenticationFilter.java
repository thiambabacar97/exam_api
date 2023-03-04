package sn.isi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mourchidtech.examjeedevops.security.properties.JwtEnvironment;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sn.isi.dto.AppUser;
import sn.isi.exception.RequestException;
import sn.isi.service.AppUserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	@Autowired
	private  JwtEnvironment jwtEnvironment;

	@Autowired
	private AppUserService userService;

	@Autowired
	private MessageSource messageSource;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AppUser appUser = null;
		try {
			appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		} catch (Exception e) {
			throw new RequestException("Bad credentials", HttpStatus.FORBIDDEN);
		}

		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword())
		);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			    Authentication authResult) throws IOException, ServletException {
		User userSpring = (User)authResult.getPrincipal();

		String jwt = Jwts.builder()
				.setSubject(userSpring.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+ SecurityConstant.EXPIRATION_TIME * 100))
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.getSecret())
				.claim("roles", userSpring.getAuthorities())
				.compact();

		response.setHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX+jwt);
	}
}
