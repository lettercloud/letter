package org.letter.cloud.spring.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.letter.metrics.MetricsFactory;
import org.letter.metrics.api.MetricsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * <filter>
 * <filter-name>metricsFilter</filter-name>
 * <filter-class>org.letter.springboot.filter.MetricsFilter</filter-class>
 * </filter>
 * <filter-mapping>
 * <filter-name>metricsFilter</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 *
 * @author wuhao
 */
public class MetricsFilter implements Filter {
	private static final String METRICS_NAME = "http";
	private static final String METRICS_SUCCESS = "success";
	private static final String METRICS_FAIL = "fail";
	private static final Logger LOGGER = LoggerFactory.getLogger(MetricsFilter.class);

	/**
	 * 容器加载的时候调用
	 *
	 * @param filterConfig
	 * @throws ServletException
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI().replace("/", "/");
		try (MetricsContext timer = MetricsFactory.timer(METRICS_NAME, METRICS_NAME, url, METRICS_SUCCESS).time()) {
			chain.doFilter(request, response);
		} catch (IOException e) {
			throw e;
		} catch (ServletException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 容器被销毁的时候被调用
	 */
	@Override
	public void destroy() {

	}

}
