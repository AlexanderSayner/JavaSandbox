package sayner.sandbox.repositories.impl;

import sayner.sandbox.models.Article;
import sayner.sandbox.repositories.ArticleRepoHibernate;
import sayner.sandbox.utils.HibernateSessionFactoryUtil;

public class ArticleRepoHibernateImpl implements ArticleRepoHibernate {

    @Override
    public Article findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Article.class, id);
    }
}