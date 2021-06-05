package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        try {
            /***
             * Member CREATE
             */
            // Member member = new Member();
            // member.setId(2L);
            // member.setName("kwonyong");
            // entityManager.persist(member);

            /***
             * Member READ
             */
//            Member findMember = entityManager.find(Member.class, 2L);
//            System.out.println(findMember.getId());
//            System.out.println(findMember.getName());

            /***
             * Member DELETE
             */
            // entityManager.remove(findMember);

            /***
             * Member UPDATE --> commit시점에서 변경사항 체크 후 update query 생성
             */
//            findMember.setName("hansol");

            Member member = new Member();
            member.setUsername("minsu");
            member.setRoleType(RoleType.GUEST);

            entityManager.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
