package platform.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String date;

    private Long time;
    private Long views;

    @JsonIgnore
    private Long startingTime;

    @JsonIgnore
    private boolean viewsInit;

    @JsonIgnore
    private String lastAccessed;

    @JsonIgnore
    private String Uuid;
}
