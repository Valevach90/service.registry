package com.andersen.banking.service.registry.meeting_impl.config;


import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import org.springframework.context.annotation.Bean;

public class JaegerConfiguration {

    @Bean
    public JaegerTracer jaegerTracer(){

        return new io.jaegertracing.Configuration("order-client")
                .withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE).withParam(1))
                .withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true))
                .getTracer();
    }

}
