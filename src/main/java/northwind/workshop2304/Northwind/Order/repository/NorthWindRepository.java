package northwind.workshop2304.Northwind.Order.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static northwind.workshop2304.Northwind.Order.repository.Queries.*;

@Repository
public class NorthWindRepository {

    @Autowired
    private JdbcTemplate template;

    public SqlRowSet getOrderDetails(Integer orderId) {
        SqlRowSet result = template.queryForRowSet(
            SQL_SEARCH_ORDER_AND_ORDER_DETAILS, orderId);
        
        return result;
    }    
}
