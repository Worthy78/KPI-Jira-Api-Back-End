package com.example.jira.service.dto;

import com.example.jira.domain.Board;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class BoardSerializer extends StdSerializer<Board> {

    protected BoardSerializer(Class<Board> t) {
        super(t);
    }

    protected BoardSerializer(JavaType type) {
        super(type);
    }

    protected BoardSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected BoardSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(Board board, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
