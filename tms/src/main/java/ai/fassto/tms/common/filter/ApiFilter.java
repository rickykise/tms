package ai.fassto.tms.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class ApiFilter implements Filter {
    private static final List<String> excludePath = List.of("/api/status/tms/health", "/");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        MDC.put("trace_id", UUID.randomUUID().toString());

        if (!excludePath.contains(((HttpServletRequest) servletRequest).getRequestURI())) {
            log.info("API Filter Url : {}", ((HttpServletRequest) servletRequest).getRequestURI());
        }

        filterChain.doFilter(servletRequest, servletResponse);

        MDC.clear();
    }
}