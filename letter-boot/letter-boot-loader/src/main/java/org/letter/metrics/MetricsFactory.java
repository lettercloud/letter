package org.letter.metrics;

import org.letter.common.extension.ExtensionLoader;
import org.letter.common.utils.ConfigUtils;
import org.letter.metrics.api.*;

/**
 * MetricsFactory
 *
 * @author wuhao
 */

public class MetricsFactory {
	public static MetricsBuilder BUILDER = ExtensionLoader.loadOrByDefaultFactory(MetricsBuilder.class,
			ConfigUtils.getProperty(MetricsBuilder.KEY, MetricsBuilder.DEFAULT), MetricsBuilderNop::new);

	public static MetricsTimer timer(String scope, String... names) {
		return BUILDER.timer(scope, names);
	}

	public static MetricsCounter counter(String scope, String... names) {
		return BUILDER.counter(scope, names);
	}

	public static MetricsMeter meter(String scope, String... names) {
		return BUILDER.meter(scope, names);
	}

	public static MetricsHistogram histogram(String scope, String... names) {
		return BUILDER.histogram(scope, names);
	}

	public static <T> void gauge(MetricsGauge<T> metricsGauge, String scope, String... names) {
		BUILDER.gauge(metricsGauge, scope, names);
	}
}
