package de.jablab.sebschlicht.android.kits.commands;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class CommandTypeIdResolver implements TypeIdResolver {

    private JavaType baseType;

    @Override
    public String getDescForKnownTypeIds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Id getMechanism() {
        return Id.CUSTOM;
    }

    @Override
    public String idFromBaseType() {
        return idFromValueAndType(null, baseType.getRawClass());
    }

    @Override
    public String idFromValue(Object value) {
        return idFromValueAndType(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        if (value == null) {
            return null;
        }
        if (value instanceof CommandType) {
            return value.toString();
        }
        throw new IllegalStateException("Unsupported value of type \""
                + value.getClass().getName() + "\"!");
    }

    @Override
    public void init(JavaType baseType) {
        this.baseType = baseType;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id)
            throws IOException {
        CommandType type = CommandType.parseString(id);
        if (type == null) {
            throw new IllegalStateException(
                    "Illegal command type identifier \"" + id + "\"!");
        }
        return TypeFactory.defaultInstance().constructSpecializedType(baseType,
                type.getType());
    }
}
