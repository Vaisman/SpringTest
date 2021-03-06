package com.svirski.spring.core.aspects.mocks;

import com.svirski.spring.core.aspects.CounterAspect;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 13/2/16
 * Time: 8:42 PM
 */
public class CountAspectMock extends CounterAspect {

    public static void cleanup() {
        accessByNameCounter.clear();
        getPriceByNameCounter.clear();
        bookByNameCounter.clear();
    }
}
