package com.astro.ai.astroai.standards.util;

import com.astro.ai.astroai.standards.masking.MaskData;
import com.astro.ai.astroai.standards.masking.MaskSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class DisablingJsonPropertyIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public PropertyName findNameForSerialization(Annotated annotation){

        if( annotation.hasAnnotation(JsonProperty.class) ){
            return PropertyName.USE_DEFAULT;
        } else{
            return null;
        }
    }

    @Override
    public Object findSerializer(Annotated am) {

        if(am.hasAnnotation(MaskData.class)) {
            return MaskSerializer.MaskSensitiveDataSerializer.class;
        }

        return super.findSerializer( am );
    }

    @Override
    public Object findDeserializer(Annotated am) {
        if(am.hasAnnotation(MaskData.class)) {
            return MaskSerializer.MaskSensitiveDataDeserializer.class;
        }

        return super.findDeserializer( am );
    }
}
