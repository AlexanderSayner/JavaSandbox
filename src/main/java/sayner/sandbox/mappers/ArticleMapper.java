package sayner.sandbox.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import sayner.sandbox.dto.ArticleDTO;
import sayner.sandbox.models.Article;

import java.util.Collection;


@Component
@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    /**
     * target is that is returning, source - that is getting
     *
     * @param article
     * @return
     */
    @Mappings({
            @Mapping(source = "name", target = "full_name"),
            @Mapping(source = "id", target = "serial_id")
    })
    ArticleDTO toArticleDTO(Article article);

    Collection<ArticleDTO> toArticleDTOs(Collection<Article> articles);

    @InheritInverseConfiguration
    Article toArticle(ArticleDTO articleDTO);
}