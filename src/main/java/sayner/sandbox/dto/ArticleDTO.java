package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import sayner.sandbox.jsontemplate.jview.ArticleViewDto;
import sayner.sandbox.models.Warehouse;
import sayner.sandbox.models.enums.ArticleState;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;


//    /**
//     *
//     */
//    /*
//    Formats the date when the DTO is output to JSON.
//    If this is not used the JSON will display a number
//    that represents the time instead of a easy to read string.
//     */
//    @JsonFormat(pattern = "MM-yyyy-dd") // Formats output date when this DTO is passed through JSON
//    /*
//    This works the other way around as it allows the date to be input in dd/MM/yyyy format,
//    which if your trying to pass a date directly into JSON it will be hard to know the number
//    version of the date you want.
//     */
//    @DateTimeFormat(pattern = "dd/MM/yyyy") // Allows dd/MM/yyyy date to be passed into GET request in JSON
//    private LocalDateTime creationDateTime;
@NoArgsConstructor
@Data
@Log4j2
public final class ArticleDTO {

    @JsonView(ArticleViewDto.Id.class)
    private Integer articleId;

    @JsonView(ArticleViewDto.Name.class)
    private String name;

    @JsonView(ArticleViewDto.Title.class)
    private String title;

    @JsonView({ArticleViewDto.Manufacturer.class, ArticleViewDto.Id.class})
    private String manufacturer;

    @JsonView(ArticleViewDto.State.class)
    private ArticleState state;

    @JsonView(ArticleViewDto.Mass.class)
    private String massSi;

    @JsonView(ArticleViewDto.Garantee.class)
    private String guarantee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM HH:mm")
    @JsonView(ArticleViewDto.CreationDate.class)
    private LocalDateTime creationDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM HH:mm")
    @JsonView(ArticleViewDto.UpdateDate.class)
    private LocalDateTime updatedAt;

    @JsonView(ArticleViewDto.Warehouses.class)
    private Set<Warehouse> warehouses;

    /**
     * extended getter'ы & setter'ы
     */

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = Collections.unmodifiableSet(warehouses);
    }

    public ArticleDTO(Integer articleId, String name, String title, String manufacturer, ArticleState articleState, String mass_si, String guarantee, LocalDateTime creationDateTime, LocalDateTime updatedAt, Set<Warehouse> warehouses) {
        this.articleId = articleId;
        this.name = name;
        this.title = title;
        this.manufacturer = manufacturer;
        this.state = articleState;
        this.massSi = mass_si;
        this.guarantee = guarantee;
        this.creationDateTime = creationDateTime;
        this.updatedAt = updatedAt;
        this.setWarehouses(warehouses);
    }
}
