package org.letter.rpc.dubbo.filter;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.letter.metrics.MetricsFactory;
import org.letter.metrics.api.MetricsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;


/**
 * @author wuhao
 */
@Activate(group = {CONSUMER, PROVIDER}, order = 1)
public class DubboMetricsFilter implements Filter {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private static final String METRICS_NAME = "dubbo";
	private static final String METRICS_SUCCESS = "success";
	private static final String METRICS_FAIL = "fail";

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String service = invocation.getServiceName().replace(".", "_");
		String method = invocation.getMethodName();
		try (MetricsContext timer = MetricsFactory.timer(METRICS_NAME, service,
			method, METRICS_SUCCESS).time()) {
			return invoker.invoke(invocation);
		} catch (Exception e) {
			LOGGER.error("invoke fail:{} {}", service, method, e);
			MetricsFactory.meter(METRICS_NAME, service, method, METRICS_FAIL).mark();
		}
		return invoker.invoke(invocation);
	}
}
