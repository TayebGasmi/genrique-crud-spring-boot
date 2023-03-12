package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String description;
    private String image;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<Reaction> reactions;
    @JsonIgnore
    @OneToMany(mappedBy = "post")
    Set<Comment> comments;


}
