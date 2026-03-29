package cz.vibri.domain.bootstrap;

import cz.vibri.domain.joinedtable.ElectricGuitar;
import cz.vibri.domain.repositories.ElectricGuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    ElectricGuitarRepository electricGuitarRepository;

    @Override
    public void run(String... args) throws Exception {
        ElectricGuitar eg = new ElectricGuitar();
        eg.setNumberOfPickups(2);
        eg.setNumberOfStrings(6);

        electricGuitarRepository.save(eg);
    }
}
