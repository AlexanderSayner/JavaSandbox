package sayner.sandbox.repositories;

import sayner.sandbox.models.Article;
import sayner.sandbox.utils.HibernateSessionFactoryUtil;

public class ArticleRepoHibernateImpl implements ArticleRepoHibernate {

    @Override
    public Article findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Article.class, id);
    }
}