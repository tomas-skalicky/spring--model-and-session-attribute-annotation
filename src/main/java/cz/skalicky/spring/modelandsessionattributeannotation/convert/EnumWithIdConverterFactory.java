package cz.skalicky.spring.modelandsessionattributeannotation.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import cz.skalicky.spring.modelandsessionattributeannotation.support.EnumWithId;

public class EnumWithIdConverterFactory implements ConverterFactory<String, EnumWithId<?>> {

    @Override
    public <T extends EnumWithId<?>> Converter<String, T> getConverter(final Class<T> targetType) {

        return new Converter<String, T>() {

            @Override
            @SuppressWarnings("unchecked")
            public T convert(final String id) {

                try {
                    // TODO needs to be done more generic
                    Method method = targetType.getMethod("getById", int.class);
                    return (T) method.invoke(null, Integer.valueOf(id));
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}
