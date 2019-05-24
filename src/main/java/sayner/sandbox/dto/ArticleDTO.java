package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ArticleDTO {

    /**
     *
     */
    private int id;

    /**
     *
     */
    private String name;


    /**
     *
     */
    /*
    Formats the date when the DTO is output to JSON.
    If this is not used the JSON will display a number
    that represents the time instead of a easy to read string.
     */
    @JsonFormat(pattern = "MM-yyyy-dd") // Formats output date when this DTO is passed through JSON
    /*
    This works the other way around as it allows the date to be input in dd/MM/yyyy format,
    which if your trying to pass a date directly into JSON it will be hard to know the number
    version of the date you want.
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy") // Allows dd/MM/yyyy date to be passed into GET request in JSON
    private Date anyDate;


    /**
     *
     */
    public ArticleDTO(){}

    /**
     *
     * @param id
     * @param name
     */
    public ArticleDTO(int id, String name) {

        this.id = id;
        this.name = name;

        LocalDate now = LocalDate.now();
        this.anyDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAnyDate() {
        return anyDate;
    }

    public void setAnyDate(Date anyDate) {
        this.anyDate = anyDate;
    }
}
