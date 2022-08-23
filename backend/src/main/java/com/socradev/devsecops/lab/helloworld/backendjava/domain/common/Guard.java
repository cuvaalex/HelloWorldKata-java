package com.socradev.devsecops.lab.helloworld.backendjava.domain.common;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.common.guards.StringGuards;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Guard {
    public static StringGuards guard(String value) {
        return new StringGuards(value);
    }


}
