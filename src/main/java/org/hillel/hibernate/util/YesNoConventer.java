package org.hillel.hibernate.util;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class YesNoConventer implements AttributeConverter<Boolean, String> {

    private enum YesNoType{
        YES("yes", true), NO("no", false);
        private final String dbValue;
        private final boolean entityValue;

        YesNoType(String dbValue, boolean entityValue){
            this.dbValue = dbValue;
            this.entityValue = entityValue;
        }
        private static final YesNoType getByDBValue(String value){
            if(StringUtils.isEmpty(value)) return NO;
            for(YesNoType type: values()){
                if (Objects.equals(type.dbValue, value)) return type;
            }
            return NO;
        }
    }

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return null;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return null;
    }
}
