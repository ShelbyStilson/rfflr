package rfflr.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer employee;

    @NotNull
    private Integer ticketNum;

    @NotNull
    private Integer raffleId;

    public String toJson(){
        return "{\"id\": " + this.id + ", "
                + "\"employee\": \"" + this.employee + "\", "
                + "\"ticketNum\": \"" + this.ticketNum + "\", "
                + "\"raffleId\": \"" + this.raffleId + "\", "
                + "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Integer getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(Integer raffleId) {
        this.raffleId = raffleId;
    }
}
