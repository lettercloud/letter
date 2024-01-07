package org.letter.metrics.simple;


import org.letter.common.utils.NetUtils;
import org.letter.metrics.MetricsFactory;
import org.letter.metrics.api.MetricsContext;
import org.letter.metrics.api.MetricsMeter;
import org.letter.metrics.api.MetricsTimer;
import org.letter.metrics.config.MetricsAutoRegistration;
import org.letter.metrics.config.MetricsConfigProperties;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

import java.io.IOException;
import java.util.Random;

/**
 * MonitorExporterSimple
 *
 * @author wuhao
 */
public class MonitorExporterSimple {
	public static void main(String[] args) throws IOException {
		//启动监控server
		//initJmx();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				NetUtils.getLocalAddress();
				//MonitorExporter monitorExporter = new MonitorExporter(new MetricsConfigProperties());
				MetricsAutoRegistration registration = new MetricsAutoRegistration();
				registration.startRegAndExporter(new MetricsConfigProperties());
			}
		});
		thread.start();

		doPrometheus();
		doDropwizard();


	}

	public static void initJmx() {
		System.setProperty("java.rmi.server.hostname", "localhost");

	}

	public static void doDropwizard() {
		//dropwizard
		MetricsTimer timer = MetricsFactory.timer("rpc", "interface");
		MetricsMeter meter = MetricsFactory.meter("rpc", "interface");
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					MetricsContext context = timer.time();
					try {
						int i = new Random().nextInt(20);
						meter.mark();
						Thread.sleep(i);
						context.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();

	}

	public static void doPrometheus() {
		//prometheus
		Counter requests = Counter.build().name("rpc_prometheus")
			.labelNames("rpc", "service", "method")
			.help("Total requests.").register();
		Summary requestLatency = Summary.build()
			.name("rpc_prometheus_timer")
			.help("request latency in seconds")
			.quantile(0.5, 0.01)    // 0.5 quantile (median) with 0.01 allowed error
			.quantile(0.95, 0.005)  // 0.95 quantile with 0.005 allowed error
			.labelNames("rpc", "service", "method")
			.register();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					try {
						int i = new Random().nextInt(20);
						requests.labels("rpc" + i, "service" + i, "method" + i).inc();
						Summary.Timer timer1 = requestLatency.labels("rpc" + i, "service" + i, "method" + i).startTimer();
						timer1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();

	}
}
