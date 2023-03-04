package sn.isi.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstant {

    // @Value("${jwt.secret}")
	public static final String SECRETE = "420baf6b-fd57-4d1f-92bc-107772e601a1d5cfd091-8a7b-4156-9d9c-1601a88820faf3ae1ff2-607d-4cce-be50-cf084634252f";
    public static final long EXPIRATION_TIME = 1_999_999_999;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getSecret() {
        return "SECRETE";
    }
}
