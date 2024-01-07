package org.letter.metrics;

import org.junit.jupiter.api.Test;
import org.letter.metrics.api.*;

/**
 * MetricsFactoryTest
 *
 * @author wuhao
 */

public class MetricsBuilderSimple implements MetricsBuilder{
	@Test
	void testMetrics() {

	}

	@Override
	public MetricsTimer timer(String scope, String... names) {
		return null;
	}

	@Override
	public MetricsCounter counter(String scope, String... names) {
		return null;
	}

	@Override
	public MetricsMeter meter(String scope, String... names) {
		return null;
	}

	@Override
	public MetricsHistogram histogram(String scope, String... names) {
		return null;
	}

	@Override
	public <T> void gauge(MetricsGauge<T> metric, String scope, String... names) {

	}


}
