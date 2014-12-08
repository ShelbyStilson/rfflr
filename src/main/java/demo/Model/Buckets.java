package demo.Model;

import java.util.List;

public class Buckets {
    private Integer id;
    private Integer raffleId;
    private Integer bucketId;
    private Integer entry;
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
