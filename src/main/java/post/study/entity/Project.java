package post.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String projectName;
    private String projectLeader;
    private String scale;
    private String topic;
}
