package guru.springframework.creditcard.domain;

import guru.springframework.creditcard.config.SpringContextHelper;
import guru.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        System.out.println("Converter encrypt");
        return getEncryptionService().encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        System.out.println("Converter decrypt");
        return getEncryptionService().decrypt(dbData);
    }

    private EncryptionService getEncryptionService() {
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}
