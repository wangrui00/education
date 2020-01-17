package com.education.config;

import brave.Tracing;
import brave.context.slf4j.MDCScopeDecorator;
import brave.http.HttpTracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.servlet.TracingFilter;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import javax.management.MXBean;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangrui
 * @ClassName TracingConfiguration
 * @description 链路跟踪配置
 * @date 2020/1/10 11:00
 **/
@Configuration
@Import(SpanCustomizingAsyncHandlerInterceptor.class)
public class TracingConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    Sender sender(){
        //http://tracing-analysis-dc-bj-internal.aliyuncs.com/adapt_gz8ytllbno@20ffa59de9a080d_gz8ytllbno@53df7ad2afe8301/api/v2/spans
        return OkHttpSender.create("http://tracing-analysis-dc-bj-internal.aliyuncs.com/adapt_gz8ytllbno@20ffa59de9a080d_gz8ytllbno@53df7ad2afe8301/api/v2/spans");
    }

    @Bean
    AsyncReporter<Span> spanReporter() {
        return AsyncReporter.create(sender());
    }

    @Bean
    Tracing tracing(@Value("${spring.application.name}") String serviceName) {
        return Tracing.newBuilder()
                .localServiceName(serviceName)
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                        .addScopeDecorator(MDCScopeDecorator.create())
                        .build()
                )
                .spanReporter(spanReporter()).build();
    }

    @Bean
    HttpTracing httpTracing(Tracing tracing) {
        return HttpTracing.create(tracing);
    }

    @Bean
    BeanPostProcessor connectionFactoryDecorator(final BeanFactory beanFactory) {
        return new BeanPostProcessor() {
            @Override public Object postProcessBeforeInitialization(Object bean, String beanName) {
                return bean;
            }

            @Override public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (!(bean instanceof RestTemplate)) {
                    return bean;
                }
                RestTemplate restTemplate = (RestTemplate) bean;
                List<ClientHttpRequestInterceptor> interceptors =
                        new ArrayList<>(restTemplate.getInterceptors());
                interceptors.add(0, getTracingInterceptor());
                restTemplate.setInterceptors(interceptors);
                return bean;
            }

            ClientHttpRequestInterceptor getTracingInterceptor() {
                return TracingClientHttpRequestInterceptor.create(beanFactory.getBean(HttpTracing.class));
            }
        };
    }

    @Bean
    Filter tracingFilter(HttpTracing httpTracing) {
        return TracingFilter.create(httpTracing);
    }

    @Autowired
    SpanCustomizingAsyncHandlerInterceptor webMvcTracingCustomizer;

    @Override public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMvcTracingCustomizer);
    }
}
