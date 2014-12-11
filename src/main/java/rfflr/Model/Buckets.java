package rfflr.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "buckets")
public class Buckets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer raffleId;

    @NotNull
    private Integer bucketId;

    @NotNull
    private Integer entry;

    @NotNull
    private Integer tickets;

    public String toJson(){
        return "{\"id\": " + this.id + ", "
                + "\"raffleId\": \"" + this.raffleId + "\", "
                + "\"bucketId\": \"" + this.bucketId + "\", "
                + "\"entry\": \"" + this.entry + "\" "
                + "\"tickets\": \"" + this.tickets + "\" "                
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

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }
}
