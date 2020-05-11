package lt.codeacademy.springmvc.repositories;

import lt.codeacademy.springmvc.controller.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class DbProductsDao implements ProductsDao {

//    private DataSource dataSource;
//
//    public DbProductsDao(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private JdbcTemplate jdbcTemplate;

    public DbProductsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> getProduct(Long id) {

        return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM Products WHERE product_id = ?",
                        new Object[]{id},
                        (resultSet, rowNum) -> mapToProduct(resultSet, rowNum))
        );
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

//    @Override
//    public List<Product> getProducts() {
//
//        List<Product> result = new ArrayList<>();
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement("SELECT * FROM Products;");
//            resultSet = preparedStatement.executeQuery();
//
//            while(resultSet.next()) {
//                Product product = new Product();
//                product.setId(resultSet.getLong("product_id"));
//                product.setTitle(resultSet.getString("title"));
//                product.setPrice(resultSet.getDouble("price"));
//                product.setDescription(resultSet.getString("description"));
//
//                result.add(product);
//            }
//
//        } catch (SQLException e) { } finally {
//            try {
//                if (resultSet != null)
//                    resultSet.close();
//                if (preparedStatement != null)
//                    preparedStatement.close();
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e2) {}
//        }
//
//        return result;
//    }

    @Override
    public List<Product> getProducts() {
        return jdbcTemplate.query("SELECT * FROM Products;",
                (resultSet, rowNum) -> mapToProduct(resultSet, rowNum));
    }

    private Product mapToProduct(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("product_id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getDouble("price"));
        product.setDescription(resultSet.getString("description"));

        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Products(title, description, price) VALUES(?, ?, ?);",
                    new String[] {"product_id"}
                    );
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());

            return ps;
        }, keyHolder);

        return getProduct(keyHolder.getKey().longValue()).get();
    }
}
