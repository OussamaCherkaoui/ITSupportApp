package com.itSupport.ITsupportApp;

import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.repository.EquipementRepository;
import com.itSupport.ITsupportApp.service.EquipementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EquipementTest {
    @Autowired
    private EquipementService equipementService;

    @MockBean
    private EquipementRepository equipementRepository;


    @BeforeEach
    void SetUp(){

    }

    @Test
    void testGetAllEvents()
    {
        Equipement equipement = new Equipement();
        when(equipementRepository.findAll()).thenReturn(List.of(equipement));

        List<Equipement> equipements = equipementService.getAll();

        assertNotNull(equipements);
        assertFalse(equipements.isEmpty());
    }


    @Test
    void getEquipementById()
    {
        Equipement equipement = new Equipement();
        Long id = 1L;
        when(equipementRepository.findById(id)).thenReturn(Optional.of(equipement));

        equipement = equipementService.getById(id);

        assertNotNull(equipement);
    }
}
