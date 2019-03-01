package com.battlesnake.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Logs total time taken to perform request.
 *
 * <p>Adapted from: <a href=
 * "https://stackoverflow.com/a/16050835/9129020"
 * >ResponseTimeFilter</a>
 */
public class ResponseTimeFilter implements Filter {

    private static Logger log = LogManager.getLogger();

    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
        throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long elapsed = System.currentTimeMillis() - startTime;
        if (request instanceof HttpServletRequest) {
           String name = ((HttpServletRequest) request).getRequestURI();
           log.info("{} completed in {} ms", name, elapsed);
        }
    }
}
