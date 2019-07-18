package sayner.sandbox.repositories.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;
import sayner.sandbox.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArticleRepoHibernateImpl implements ArticleRepoHibernate {

    @Override
    public Article findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Article.class, id);
    }

    @Override
    public List<Article> findAllOrderByNameUsingCriteriaQuery(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.like(root.get("name"), "%" + name + "%"))
                .orderBy(criteriaBuilder.desc(root.get("title")));

        Query<Article> articleQuery = session.createQuery(criteriaQuery);
        List<Article> articleList = articleQuery.getResultList();
        return articleList;
    }
}