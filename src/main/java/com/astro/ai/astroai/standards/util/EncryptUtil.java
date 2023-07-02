package com.astro.ai.astroai.standards.util;

import com.astro.ai.astroai.standards.logging.CustomLogManager;
import com.astro.ai.astroai.standards.logging.CustomLogger;
import com.astro.ai.astroai.standards.logging.LogMessage;
import com.astro.ai.astroai.standards.masking.MaskSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;

public class EncryptUtil {

    private static final String AES = "AES";
    private static final String SECRET = "secret-key-12345";
    private static Key key;

    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectMapper encryptMapper = new ObjectMapper();



    private static final CustomLogger logger = CustomLogManager.getLogger(EncryptUtil.class);

    public static String encryptMessage(String unencryptedString) {
        String encryptedString = null;
        try {
            key = new SecretKeySpec(SECRET.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedString =  java.util.Base64.getEncoder().encodeToString(cipher.doFinal(unencryptedString.getBytes("UTF-8")));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            logger.error(new LogMessage.LogMessageBuilder("EncryptUtil => encryptMessage called with InvalidKeyException or BadPaddingException or IllegalBlockSizeException Exception")
                    .build(),e);
            throw new IllegalStateException(e);
        }  catch(Exception e){
            logger.error(new LogMessage.LogMessageBuilder("EncryptUtil => encryptMessage called with Exception")
                    .build(),e);
        }
        if(encryptedString==null){
            return unencryptedString;
        }
        return encryptedString;
    }

    public static  String decryptMessage(String encryptedMessage){
        String decryptedText=null;
        try {
            key = new SecretKeySpec(SECRET.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            logger.error(new LogMessage.LogMessageBuilder("EncryptUtil => decryptMessage called with InvalidKeyException or BadPaddingException or IllegalBlockSizeException Exception")
                    .build(),e);
            throw new IllegalStateException(e);
        } catch(Exception e){
            logger.error(new LogMessage.LogMessageBuilder("EncryptUtil => decryptMessage called with Exception")
                    .build(),e);
        }
        if(decryptedText==null){
            return encryptedMessage;
        }
        return decryptedText;
    }

    public static ObjectMapper objectMapper() {
        return generateObjectMapper(mapper);
    }

    private static ObjectMapper generateObjectMapper(ObjectMapper mapper) {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setAnnotationIntrospector(new MaskSerializer.MaskSensitiveDataAnnotationIntrospector());

        return mapper;
    }

    public static ObjectMapper objectMapper(boolean isIgnoreJsonProperty){
        encryptMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        encryptMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        encryptMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        if( isIgnoreJsonProperty ){
            encryptMapper.setAnnotationIntrospector( new DisablingJsonPropertyIntrospector() );
        }
        return encryptMapper;
    }


    public static <T> JsonNode getMaskedJson(T payload) {
        try {
            ObjectMapper objectMapper = EncryptUtil.objectMapper();
            String pay = objectMapper.writeValueAsString(payload);
            JsonNode payloadJson = objectMapper.readTree(pay);
            return payloadJson;
        } catch(Exception e){
            logger.error(new LogMessage.LogMessageBuilder("EncryptUtil => getMaskedJson called with Exception")
                    .build(),e);
        }
        return null;
    }

    public static <T> T getUnMaskedJson(String payload, Class<T> cls) {

        try {
            ObjectMapper objectMapper = EncryptUtil.objectMapper();
            return objectMapper.readValue(payload, cls);
        } catch(Exception e){
            logger.error(new LogMessage.LogMessageBuilder("EncryptUtil => getMaskedJson called with Exception")
                    .build(),e);
        }
        return null;

    }
}
