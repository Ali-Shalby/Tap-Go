package com.tapngo.dao;

import com.tapngo.exception.DAOException;
import com.tapngo.model.dao.RecuperaPiattiDAO;
import com.tapngo.model.domain.ListPiatti;

import com.tapngo.model.domain.Ristorante;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RecuperaPiattiDAOTest {
    @Test
    void recuperaPiattiSuccessfull(){
        Ristorante filtro = new Ristorante("taverna flavia");

        ListPiatti listPiatti = null;
        RecuperaPiattiDAO recuperaPiatti = new RecuperaPiattiDAO();
        try {
            listPiatti = recuperaPiatti.recuperaPiattiExecute(filtro);
        }catch (DAOException | SQLException e){
            fail();
        }
        assertNotEquals(null,listPiatti);
    }

}
