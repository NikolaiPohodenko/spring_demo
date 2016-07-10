package craft.spring_demo.model;

/**
 * Created by phdnk on 09-Jul-16.
 */
import javax.persistence.*;

@Entity
@Table(name = "RESPONCES")
public class RecordEntity {

    @Id
    @Column(name = "id")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "userID")
    private int userId;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name = "title")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "body")
    private String body;
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }



    @Override
    public String toString() {
        return "RecordEntity" + "(" + getId() + ")";
    }


    @Override
    public boolean equals(Object othr) {
        if (this == othr) return true;
        if (othr == null || getClass() != othr.getClass()) return false;
        RecordEntity other = (RecordEntity) othr;

        if (    id != other.    id) return false;
        if (userId != other.userId) return false;
        if ( title != null ? !title.equals(other.title) : other.title != null) return false;
        if (  body != null ? ! body.equals(other. body) : other. body != null) return false;
        return true;
    }


    @Override
    public int hashCode() {
        int a = 31;
        int t_hash = (getTitle() != null ? getTitle().hashCode() : 0);
        int b_hash = ( getBody() != null ?  getBody().hashCode() : 0);
        return (getUserId()*a + t_hash)*a + b_hash;
    }
}
