package tn.esprit.spring.services;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Piste;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class PisteServicesImplTest {

    @Autowired
    private PisteServicesImpl pisteService;

    @Test
    @DatabaseSetup("/data-set/pistes.xml")
    @Order(1)
    void retrieveAllPistes() {
        final List<Piste> pistes = pisteService.retrieveAllPistes();
        assertEquals(pistes.size(), 1);
    }

    @Test
    @DatabaseSetup("/data-set/pistes.xml")
    @Order(2)
    void addPiste() {
        final Piste piste = new Piste();
        piste.setNamePiste("Added Piste");
        this.pisteService.addPiste(piste);
        assertEquals(this.pisteService.retrievePiste(2L).getNamePiste(), "Added Piste");
    }

    @Test
    @DatabaseSetup("/data-set/pistes.xml")
    @Order(4)
    void removePiste() {
        this.pisteService.removePiste(1L);
        final List<Piste> pistes = pisteService.retrieveAllPistes();
        assertEquals(pistes.size(), 0);
    }

    @Test
    @DatabaseSetup("/data-set/pistes.xml")
    @Order(3)
    void retrievePiste() {
        final Piste piste = this.pisteService.retrievePiste(1L);
        assertEquals(piste.getNamePiste(), "Piste de test");
    }
}