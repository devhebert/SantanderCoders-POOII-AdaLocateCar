package com.ADALocateCar.services.rent.dashboard;

import com.ADALocateCar.models.Rent;
import com.ADALocateCar.repositories.RentRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class RentDashboardView {
    private RentRepository rentRepository = null;

    public RentDashboardView(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<Map<String, Object>> getRentInformation() {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "password";
        Result result = new Result(url, user, password);

        try {
            Connection connection = DriverManager.getConnection(result.url(), result.user(), result.password());

            String sql = "SELECT " +
                    "c.id AS customerId, " +
                    "c.fullname AS fullname, " +
                    "c.cpf_cnpj AS cpfCnpj, " +
                    "v.id AS vehicleId, " +
                    "v.plate AS plate, " +
                    "v.model AS model, " +
                    "r.id AS rentId, " +
                    "r.payment_value AS paymentValue, " +
                    "r.return_date AS returnDate, " +
                    "r.withdraw_date AS withdrawDate, " +
                    "r.pick_up_location AS pickUpLocation, " +
                    "r.return_location AS returnLocation " +
                    "FROM rent r " +
                    "LEFT JOIN customer c ON c.ID = r.customer_id " +
                    "LEFT JOIN vehicle v ON v.ID = r.vehicle_id;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            List<Map<String, Object>> resultados = new ArrayList<>();

            while (resultSet.next()) {
                Map<String, Object> resultado = new HashMap<>();
                resultado.put("rent_id", resultSet.getInt("rentId")); // Use the alias "rentId"
                resultado.put("customer_id", resultSet.getInt("customerId")); // Use the alias "customerId"
                resultado.put("vehicle_id", resultSet.getInt("vehicleId")); // Use the alias "vehicleId"
                resultado.put("return_date", resultSet.getDate("returnDate")); // Use the alias "returnDate"
                resultado.put("withdraw_date", resultSet.getDate("withdrawDate")); // Use the alias "withdrawDate"
                resultado.put("pick_up_location", resultSet.getString("pickUpLocation")); // Use the alias "pickUpLocation"
                resultado.put("return_location", resultSet.getString("returnLocation")); // Use the alias "RETURN_LOCATION"
                resultado.put("fullname", resultSet.getString("fullname")); // Use the alias "fullname"
                resultado.put("cpf_cnpj", resultSet.getString("cpfCnpj")); // Use the alias "cpfCnpj"
                resultado.put("plate", resultSet.getString("plate")); // Use the alias "plate"
                resultado.put("model", resultSet.getString("model")); // Use the alias "model"
                resultado.put("payment_value", resultSet.getString("paymentValue")); // Use the alias "model"

                resultados.add(resultado);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return resultados;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteRent(String id) {
        Long convertedId = Long.parseLong(id);
        Optional<Rent> existingRent = rentRepository.findById(convertedId);

        if (existingRent.isEmpty()) {
            return false;
        }

        rentRepository.delete(existingRent.get());
        return true;
    }

    private record Result(String url, String user, String password) {}

}