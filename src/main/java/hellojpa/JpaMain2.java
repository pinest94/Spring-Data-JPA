package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/***
 * author: pinest94
 * date: 2021-05-30
 */
public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        try {

            // 비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("edy");

            // 영속
            System.out.println("=== BEFORE ===");
            entityManager.persist(member);
            System.out.println("=== AFTER ===");

            Member findMember = entityManager.find(Member.class, 100L);

            System.out.println("id = " + findMember.getId());
            System.out.println("name = " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
