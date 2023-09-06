package edu.javacourse.city.dao;

import com.sun.jdi.connect.spi.Connection;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonCheckDao {

    private static final String SQL_REQUEST = "select temporal from cr_address_person ap \" +\n" +
            "                    \"inner join cr_person p on p.person_id = ap.person_id \" +\n" +
            "                    \"inner join cr_address a on a.address_id = ap.address_id \" +\n" +
            "                    \"where \" +\n" +
            "                    \"CURRENT_DATE >= ap.start_date and (CURRENT_DATE <= ap.end_date or ap.end_date is null)\" +\n" +
            "                    \"and upper(p.sur_name COLLATE \\\"en_US.UTF-8\\\") = upper(? COLLATE \\\"en_US.UTF-8\\\")  \" +\n" +
            "                    \"and upper(p.given_name COLLATE \\\"en_US.UTF-8\\\") = upper(? COLLATE \\\"en_US.UTF-8\\\")  \" +\n" +
            "                    \"and upper(patronymic COLLATE \\\"en_US.UTF-8\\\") = upper(? COLLATE \\\"en_US.UTF-8\\\")  \" +\n" +
            "                    \"and p.date_of_birth = ? \" +\n" +
            "                    \"and a.street_code = ?  \" +\n" +
            "                    \"and upper(a.building COLLATE \\\"en_US.UTF-8\\\") = upper(? COLLATE \\\"en_US.UTF-8\\\")  ";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        try(Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_REQUEST)) {

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException ex) {
            throw new PersonCheckException(ex);
        }
        return response;
    }

}
