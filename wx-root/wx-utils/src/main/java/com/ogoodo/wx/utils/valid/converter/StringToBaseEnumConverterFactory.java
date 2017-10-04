package com.ogoodo.wx.utils.valid.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Description： 将String转换为枚举类型
 */
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, IBaseEnum> {
    @Override
    public <T extends IBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        if (!targetType.isEnum()) {
            throw new UnsupportedOperationException("只支持转换到枚举类型");
        }
        return new StringToBaseEnumConverter<T>(targetType);
//      return new StringToBaseEnumConverter(targetType);
    }

    private class StringToBaseEnumConverter<T extends IBaseEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        public StringToBaseEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String s) {
            for (T t : enumType.getEnumConstants()) {
                if (s.equals(t.getName())) {
                    return t;
                }
            }
            return null;
        }
    }
}
