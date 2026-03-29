package guruspringframework.sdjpamultidb.services;

import guruspringframework.sdjpamultidb.domain.cardholder.CreditCardHolder;
import guruspringframework.sdjpamultidb.domain.creditcard.CreditCard;
import guruspringframework.sdjpamultidb.domain.pan.CreditCardPAN;
import guruspringframework.sdjpamultidb.repositories.cardholder.CreditCardHolderRepository;
import guruspringframework.sdjpamultidb.repositories.creditcard.CreditCardRepository;
import guruspringframework.sdjpamultidb.repositories.pan.CreditCardPANRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardHolderRepository creditCardHolderRepository;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardPANRepository creditCardPANRepository;

    @Transactional
    @Override
    public CreditCard getCreditCardById(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow();
        CreditCardHolder creditCardHolder = creditCardHolderRepository.findByCreditCardId(id).orElseThrow();
        CreditCardPAN creditCardPAN = creditCardPANRepository.findByCreditCardId(id).orElseThrow();

        creditCard.setFirstName(creditCardHolder.getFirstName());
        creditCard.setLastName(creditCardHolder.getLastName());
        creditCard.setZipCode(creditCardHolder.getZipCode());
        creditCard.setCreditCardNumber(creditCardPAN.getCreditCardNumber());

        return creditCard;
    }

    @Transactional
    @Override
    public CreditCard saveCreditCard(CreditCard cc) {
        CreditCard savedCc = creditCardRepository.save(cc);
        savedCc.setFirstName(cc.getFirstName());
        savedCc.setLastName(cc.getLastName());
        savedCc.setZipCode(cc.getZipCode());
        savedCc.setCreditCardNumber(cc.getCreditCardNumber());

        creditCardHolderRepository.save(CreditCardHolder.builder()
                        .creditCardId(savedCc.getId())
                        .firstName(savedCc.getFirstName())
                        .lastName(savedCc.getLastName())
                        .zipCode(savedCc.getZipCode())
                .build());

        creditCardPANRepository.save(CreditCardPAN.builder()
                        .creditCardNumber(savedCc.getCreditCardNumber())
                        .creditCardId(savedCc.getId())
                .build());
        return savedCc;
    }
}
