package com.tapngo.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.dao.RecuperaRistorantiDAO;
import com.tapngo.model.domain.ListRistoranti;
import com.tapngo.model.domain.Ristorante;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RecuperaRistorantiDAOTest {

    @Test
    void recuperaRistorantiSuccessfull(){
        Ristorante filtro = new Ristorante("taverna flavia");
        filtro.setCitta("Qualsiasi");
        filtro.setTipologia("Qualsiasi");
        filtro.setPrezzo(0);
        filtro.setValutazione(0);
        filtro.setTipo("ristorante");
        ListRistoranti listRistoranti = null;
        RecuperaRistorantiDAO recuperaRistoranti = new RecuperaRistorantiDAO();
        try {
            listRistoranti = recuperaRistoranti.recuperaRistorantiExecute(filtro);
        }catch (DAOException | SQLException e){
            fail();
        }
        assertNotEquals(null,listRistoranti);
    }


}
