package com.astro.ai.astroai.standards.masking;

import com.astro.ai.astroai.standards.util.EncryptUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MaskSerializer {

    /* map the Serializer/Deserializer based on custom annotation */

    public static class MaskSensitiveDataAnnotationIntrospector extends JacksonAnnotationIntrospector {
        private static final long serialVersionUID = 1L;

        @Override
        public Object findSerializer(Annotated am) {

            if(am.hasAnnotation(MaskData.class)) {
                return MaskSensitiveDataSerializer.class;
            }

            return super.findSerializer( am );
        }

        @Override
        public Object findDeserializer(Annotated am) {
            if(am.hasAnnotation(MaskData.class)) {
                return MaskSensitiveDataDeserializer.class;
            }

            return super.findDeserializer( am );
        }
    }

    public static class MaskSensitiveDataDeserializer extends StdDeserializer<String> {
        private static final long serialVersionUID = 1L;

        public MaskSensitiveDataDeserializer() {
            super(String.class);
        }

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            /* un-masking logic here. in our example we are removing "MASK" */
            /* string */

            String s = p.getValueAsString();
            String decrypted = EncryptUtil.decryptMessage(s);
            return decrypted;
        }
    }

    public static class MaskSensitiveDataSerializer extends StdSerializer<String> {
        private static final long serialVersionUID = 1L;

        public MaskSensitiveDataSerializer() {
            super(String.class);
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            /* Masking data; for our example we are adding 'MASK' */

            gen.writeString(EncryptUtil.encryptMessage(value));
            /* gen.writeString("MASK" + value); */
        }
    }


}
