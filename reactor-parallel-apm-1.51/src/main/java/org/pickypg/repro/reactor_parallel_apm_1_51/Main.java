package org.pickypg.repro.reactor_parallel_apm_1_51;

import co.elastic.apm.api.Traced;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Main {
    public static void main(String[] args) {
        Integer[] values = new Integer[10000];

        for (int i = 0; i < values.length; ++i) {
            values[i] = i + 1;
        }

        // TODO: Replace with Kafka stream
        Flux.fromArray(values)
            .name("parallel span issue reproduction")
            .metrics()
            .parallel()
            .runOn(Schedulers.newParallel("example", 2))
            .subscribe(Main::processValue);
    }

    private static void waitForMillis(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Traced
    private static void processValue(int value) {
        System.out.printf("Processing [%d]%n", value);
        waitForMillis(value);
    }
}
