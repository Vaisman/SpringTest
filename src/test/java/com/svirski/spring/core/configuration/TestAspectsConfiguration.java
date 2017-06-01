package com.svirski.spring.core.configuration;

import com.svirski.spring.core.aspects.CounterAspect;
import com.svirski.spring.core.aspects.DiscountAspect;
import com.svirski.spring.core.aspects.LuckyWinnerAspect;
import com.svirski.spring.core.aspects.mocks.DiscountAspectMock;
import com.svirski.spring.core.aspects.mocks.LuckyWinnerAspectMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 13/2/16
 * Time: 7:18 PM
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=false)
public class TestAspectsConfiguration extends TestBookingServiceConfiguration {

    @Bean
    public CounterAspect counterAspect() {
        return new CounterAspect();
    }

    @Bean
    DiscountAspect discountAspect() {
        return new DiscountAspectMock();
    }

    @Bean
    LuckyWinnerAspect luckyWinnerAspect() {
        return new LuckyWinnerAspectMock(99);
    }
}
