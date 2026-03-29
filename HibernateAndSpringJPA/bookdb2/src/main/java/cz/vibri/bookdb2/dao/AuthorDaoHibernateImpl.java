package cz.vibri.bookdb2.dao;

import cz.vibri.bookdb2.domain.Author;
import cz.vibri.bookdb2.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@Primary
public class AuthorDaoHibernateImpl implements AuthorDao{

    private final EntityManagerFactory emf;

    public AuthorDaoHibernateImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Author> findAll() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Author> typedQuery = em.createNamedQuery("author_find_all", Author.class);

            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Author> listAuthorByLastName(String lastName) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("SELECT a FROM Author a where a.lastName like :last_name");
            query.setParameter("last_name", lastName + "%");
            List<Author> authors = query.getResultList();

            return authors;
        } finally {
            em.close();
        }
    }

    @Override
    public Author getById(Long id) {
        EntityManager em = getEntityManager();
        Author author = em.find(Author.class, id);
        em.close();
        return author;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        EntityManager em = getEntityManager();
//        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a " +
//                "WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class);
        TypedQuery<Author> query = em.createNamedQuery("find_by_name", Author.class);

        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        Author author = query.getSingleResult();
        em.close();
        return author;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
//        em.joinTransaction();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        em.close();

        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        try (EntityManager em = getEntityManager()) {
            em.joinTransaction();
            em.merge(author);
            em.flush();
            em.clear();
            Author updated = em.find(Author.class, author.getId());
            em.close();
            return updated;
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {


        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);

            Root<Author> root = criteriaQuery.from(Author.class);

            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class);
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class);

            Predicate firstNamePred = criteriaBuilder.equal(root.get("first_name"), firstNameParam);
            Predicate lastNamePred = criteriaBuilder.equal(root.get("last_name"), lastNameParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePred, lastNamePred));

            TypedQuery<Author> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam,firstName);
            typedQuery.setParameter(lastNameParam, lastName);
            return typedQuery.getSingleResult();
        }
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        try (EntityManager em = getEntityManager()) {
            Query query = em.createNativeQuery("SELECT * FROM author a WHERE a.first_name = ? and a.last_name = ?", Author.class);
            query.setParameter(1, firstName);
            query.setParameter(2, lastName);

            return (Author) query.getSingleResult();
        }
    }

    @Override
    public List<Author> findAuthorByLastNameOrderByFirstName(String lastName, Pageable pageable) {

        try(EntityManager em = getEntityManager()) {
            TypedQuery<Author> query = null;
            if (pageable.getSort().getOrderFor("first_name").isDescending()) {
                query = em.createNamedQuery("find_by_last_name_and_sort_by_first_name_desc", Author.class);
            } else {
                query = em.createNamedQuery("find_by_last_name_and_sort_by_first_name_asc", Author.class);
            }
            query.setParameter("last_name", lastName);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());

            return query.getResultList();
        }

    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
