package org.letter.metrics;

import org.letter.common.extension.SPI;
import org.letter.metrics.api.*;

/**
 * MetricsBuilder
 *
 * @author wuhao
 */

@SPI
public interface MetricsBuilder {
	String KEY = "MetricsBuilder.KEY";
	String DEFAULT = "default";
	MetricsTimer timer(String scope, String... names);

	MetricsCounter counter(String scope, String... names);

	MetricsMeter meter(String scope, String... names);

	MetricsHistogram histogram(String scope, String... names);

	<T> void gauge(MetricsGauge<T> metric, String scope, String... names);

}
