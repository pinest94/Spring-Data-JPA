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

            Team team = new Team();
            team.setName("Team A");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("hansol");
            member.setTeam(team);
            entityManager.persist(member);

            // 이 두가지 메서드가 의미하는 바를 캐치할 것!
            entityManager.flush();
            entityManager.clear();

            // 위의 2개의 메서드를 지우면 쿼리가 나가지 않는데 왜 그러는지 이해할 것!
            // 이해가 안되면 영속성 컨텍스트 공부가 제대로 안됐다는 뜻..(복습해야지)

            Member findMember = entityManager.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("team is " + findTeam.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
