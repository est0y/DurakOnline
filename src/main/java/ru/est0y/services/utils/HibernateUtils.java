package ru.est0y.services.utils;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
@Service
@RequiredArgsConstructor
public class HibernateUtils {
    private final SessionFactory sessionFactory;
    public void doInSessionWithTransaction(Consumer<Session> action) {
        try (Session session = sessionFactory.openSession()) {
            Transaction t = session.getTransaction();
            t.begin();
            try {
                action.accept(session);
                t.commit();
            } catch (Exception e){
                t.rollback();
                throw e;
            }
        }
    }
}
