package org.letter.rpc.dubbo.filter;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.model.ServiceModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.letter.rpc.dubbo.filter.DubboMetricsFilter;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * DubboMetricsFilterTest
 *
 * @author wuhao
 */
class DubboMetricsFilterTest {
	@Test
	void testFilter() {
		try {
			DubboMetricsFilter dubboMetricsFilter = new DubboMetricsFilter();
			dubboMetricsFilter.invoke(new Invoker<Object>() {
				@Override
				public Class<Object> getInterface() {
					return null;
				}

				@Override
				public Result invoke(Invocation invocation) throws RpcException {
					return null;
				}

				@Override
				public URL getUrl() {
					return null;
				}

				@Override
				public boolean isAvailable() {
					return false;
				}

				@Override
				public void destroy() {

				}
			}, new Invocation() {
				@Override
				public String getTargetServiceUniqueName() {
					return null;
				}

				@Override
				public String getProtocolServiceKey() {
					return null;
				}

				@Override
				public String getMethodName() {
					return "testMethodName";
				}

				@Override
				public String getServiceName() {
					return "testServiceName";
				}

				@Override
				public Class<?>[] getParameterTypes() {
					return new Class[0];
				}

				@Override
				public Object[] getArguments() {
					return new Object[0];
				}

				@Override
				public Map<String, String> getAttachments() {
					return null;
				}

				@Override
				public Map<String, Object> getObjectAttachments() {
					return null;
				}

				@Override
				public Map<String, Object> copyObjectAttachments() {
					return null;
				}

				@Override
				public void foreachAttachment(Consumer<Map.Entry<String, Object>> consumer) {

				}

				@Override
				public void setAttachment(String key, String value) {

				}

				@Override
				public void setAttachment(String key, Object value) {

				}

				@Override
				public void setObjectAttachment(String key, Object value) {

				}

				@Override
				public void setAttachmentIfAbsent(String key, String value) {

				}

				@Override
				public void setAttachmentIfAbsent(String key, Object value) {

				}

				@Override
				public void setObjectAttachmentIfAbsent(String key, Object value) {

				}

				@Override
				public String getAttachment(String key) {
					return null;
				}

				@Override
				public Object getObjectAttachment(String key) {
					return null;
				}

				@Override
				public String getAttachment(String key, String defaultValue) {
					return null;
				}

				@Override
				public Object getObjectAttachment(String key, Object defaultValue) {
					return null;
				}

				@Override
				public Invoker<?> getInvoker() {
					return null;
				}

				@Override
				public void setServiceModel(ServiceModel serviceModel) {

				}

				@Override
				public ServiceModel getServiceModel() {
					return null;
				}

				@Override
				public Object put(Object key, Object value) {
					return null;
				}

				@Override
				public Object get(Object key) {
					return null;
				}

				@Override
				public Map<Object, Object> getAttributes() {
					return null;
				}

				@Override
				public void addInvokedInvoker(Invoker<?> invoker) {

				}

				@Override
				public List<Invoker<?>> getInvokedInvokers() {
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail();
		}
	}
}
