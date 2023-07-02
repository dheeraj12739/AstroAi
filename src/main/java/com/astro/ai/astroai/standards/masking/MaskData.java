package com.astro.ai.astroai.standards.masking;

public @interface MaskData {

    String value() default "***This is the Sensitive Data***";
}
