package todolist.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.Objects;

@JsonAutoDetect(setterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Item {

    private boolean done;

    private int id;

    private String description;

    private Timestamp creatingTime;

    public Item() {

    }

    public Item(@JsonProperty(value = "id")int id,
                @JsonProperty(value = "description")String description,
                @JsonProperty(value = "creating_time") Timestamp creatingTime,
                @JsonProperty(value = "done") boolean done) {
        this.done = done;
        this.id = id;
        this.description = description;
        this.creatingTime = creatingTime;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(Timestamp creatingTime) {
        this.creatingTime = creatingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return done == item.done &&
                id == item.id &&
                Objects.equals(description, item.description) &&
                Objects.equals(creatingTime, item.creatingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(done, id, description, creatingTime);
    }
}
