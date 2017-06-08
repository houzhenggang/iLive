package izuanqian.auth;


import izuanqian.ApiHeader;
import izuanqian.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 17-3-6.
 */
@Slf4j
@Component
@Order(2)
public class TokenFilter implements Filter {

    @Autowired private TokenService tokenService;
    private static final String applyTokenApi = "/token";
    @Value("${spring.profiles.active}") private String profiles;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (!"rls".equalsIgnoreCase(profiles)) {
            chain.doFilter(req, resp);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains(applyTokenApi)
                && request.getMethod().equalsIgnoreCase("POST")) {
            chain.doFilter(req, resp);
            return;
        }

        try {
            tokenService.token(
                    request.getHeader(ApiHeader.HK_TOKEN));
        } catch (Exception e) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("oops, please check your token!");
            return;
        }

        // all is ok
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
