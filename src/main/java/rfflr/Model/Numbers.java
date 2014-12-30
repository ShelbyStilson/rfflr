package rfflr.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "numbers")
public class Numbers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer raffleId;

    @NotNull
    private Integer bucketId;

    @NotNull
    private Integer numberOfEntries;

    public String toJson(){
        return "{\"id\": " + this.id + ", "
                + "\"raffleId\": \"" + this.raffleId + "\", "
                + "\"bucketId\": \"" + this.bucketId + "\", "
                + "\"numberOfEntries\": \"" + this.numberOfEntries + "\", "
                + "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(Integer raffleId) {
        this.raffleId = raffleId;
    }

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public Integer getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(Integer numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }
}