package vn.seven.stc.core.filters;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import vn.seven.stc.core.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by quocvi3t on 11/15/17.
 */
public class JwtFilter extends GenericFilterBean {
    private static Logger log = LoggerFactory.getLogger(JwtFilter.class);

//    private UserRepository userRepository;

//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String authorization = request.getHeader(Constants.AUTH_HEADER_STRING);
        if(authorization != null && authorization.startsWith(Constants.AUTH_TOKEN_PREFIX)) {
            String token = authorization.substring(Constants.AUTH_TOKEN_PREFIX.length());

            //thêm phần này vào để ktra Token mà user gửi lên đã bị cũ | để yêu cầu user đăng nhập lại
//            User user = userRepository.findFirstByJwtToken(token);
//            if(user == null){
//                throw new BadRequestAlertException("Invalid JWT signature.", "auth", "Invalid JWT signature.");
////                throw new SignatureException("Invalid JWT signature.");
//            }

            if(validateToken(token)) {
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }


    private Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Constants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        List<String> scopes = (ArrayList)claims.get(Constants.JWT_SCOPE);
        Integer tmpListLong = (Integer) claims.get(Constants.JWT_TENANT_ID);
        Integer tmpListUserId = (Integer) claims.get(Constants.JWT_USER_ID);
        Long tenantId = tmpListLong.longValue();
        Long userId = tmpListUserId.longValue();
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(String scope : scopes) {
            authorities.add(new SimpleGrantedAuthority(scope));
        }

        //User principal = new User(claims.getSubject(), "", authorities);
        UserPrincipal principal = new UserPrincipal(claims.getSubject(), "", authorities, tenantId,userId);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(Constants.JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
//            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
