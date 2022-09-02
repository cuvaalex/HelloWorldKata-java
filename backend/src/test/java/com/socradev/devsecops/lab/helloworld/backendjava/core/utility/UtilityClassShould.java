package com.socradev.devsecops.lab.helloworld.backendjava.core.utility;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.common.Guard;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationMessage;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;

public class UtilityClassShould {

    @Test
    public void notGiveAccessToConstructor() throws NoSuchMethodException {
        assertPrivateConstructor(ValidationMessage.class);
        assertPrivateConstructor(Guard.class);

    }

    private <T> void assertPrivateConstructor(Class<T> clazz) throws NoSuchMethodException {
        final Constructor<T> constructor = clazz.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        constructor.setAccessible(true);

        thenExceptionOfType(InvocationTargetException.class).isThrownBy(constructor::newInstance);
    }
}
