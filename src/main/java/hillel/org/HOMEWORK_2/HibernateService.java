
package hillel.org.HOMEWORK_2;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.time.LocalDate;

public final class HibernateService {

    final Connection con;
    Statement st;
    Homework_2 HW_2 = null;

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    EntityManager em;

    public HibernateService() throws SQLException {
        System.out.println("Creating new Hibernate Service");
        ConnectionPool connectionPool = BasicConnectionPool.create("jdbc:mysql://localhost:3306/hibernatedb", "root", "");
        con = DriverManager.getConnection(connectionPool.getUrl(), connectionPool.getUser(), connectionPool.getPassword());
        st = con.createStatement();
        em.getTransaction().begin();
        try {
            em.persist(HW_2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.close();
        emf.close();
    }
}