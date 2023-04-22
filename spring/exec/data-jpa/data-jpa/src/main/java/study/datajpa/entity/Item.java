package study.datajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@EntityListeners(AbstractMethodError.class)
public class Item implements Persistable<String> {
    @Id //@GeneratedValue
    private String id;
    @CreatedDate
    private LocalDateTime createDate;

    public Item() {
    }

    @Override
    public boolean isNew() {
        return createDate==null;
    }

    public Item(String id) {
        this.id = id;
    }
}
