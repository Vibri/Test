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

@Component
//@Primary
public class BookDaoHibernateImpl implements BookDao{

    private final EntityManagerFactory emf;

    public BookDaoHibernateImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Book findByISBN(String isbn) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Book> query = em.createNamedQuery("book_find_by_isbn", Book.class);
            query.setParameter("isbn", isbn);

            Book book = query.getSingleResult();
            return book;
        } finally {
            em.close();
        }
    }

    @Override
    public Book getById(Long id) {
        EntityManager em = getEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    @Override
    public Book findBookByTitle(String title) {
        EntityManager em = getEntityManager();
        TypedQuery<Book> query = em.createNamedQuery("book_find_by_title", Book.class);
        query.setParameter("title", title);
        Book book = query.getSingleResult();
        em.close();
        return book;
    }

    @Override
    public Book saveNewBook(Book book) {

        EntityManager em = getEntityManager();

        em.getTransaction().begin();
//        em.joinTransaction();
        em.persist(book);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        try (EntityManager em = getEntityManager()) {
            em.joinTransaction();
            em.merge(book);
            em.flush();
            em.clear();
            Book updated =  em.find(Book.class, book.getId());
            em.close();
            return updated;
        }
    }

    @Override
    public void deleteBookById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.remove(book);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Book> findAll() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Book> typedQuery = em.createNamedQuery("book_find_all", Book.class);

            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Book findBookByTitleCriteria(String title) {
        try (EntityManager em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

            Root<Book> root = criteriaQuery.from(Book.class);

            ParameterExpression<String> titleParam = criteriaBuilder.parameter(String.class);

            Predicate titlePred = criteriaBuilder.equal(root.get("title"), titleParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(titlePred));

            TypedQuery<Book> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(titleParam, title);
            return typedQuery.getSingleResult();
        }
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        try (EntityManager em = getEntityManager()){
            String hql = "SELECT b from Book b order by b.title " + pageable.getSort().getOrderFor("title").getDirection().name();
            TypedQuery<Book> query = em.createQuery(hql,Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        try (EntityManager em = getEntityManager()){
            TypedQuery<Book> query = em.createQuery("SELECT b from Book b",Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }

    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
