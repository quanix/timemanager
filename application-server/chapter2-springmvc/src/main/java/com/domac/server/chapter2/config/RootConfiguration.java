package com.domac.server.chapter2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author : lihaoquan
 */

@Configuration
@ComponentScan(
        value = {"com.domac.server.chapter2.**.service", "com.domac.server.chapter2.**.repository"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
        })
public class RootConfiguration {
}
