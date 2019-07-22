package sayner.sandbox.dto;

import org.omg.CORBA.INTERNAL;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.Optional;

public class ArticleDTO {

    /**
     *
     */
    private int serial_id;

    /**
     *
     */
    private String full_name;


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


    public ArticleDTO() {
    }

    /**
     * @param id
     * @param name
     */
    public ArticleDTO(int id, String name) {

        this.serial_id = id;
        this.full_name = name;
    }

    public int getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(int serial_id) {
        this.serial_id = serial_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {

        return "Article entity: " + this.serial_id + ", " + this.full_name + ".";
    }
}