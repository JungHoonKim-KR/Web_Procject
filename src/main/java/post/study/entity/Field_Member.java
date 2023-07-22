package post.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter@Getter
public class Field_Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String field;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Field_Member() {

    }

    public Field_Member(String field) {
        this.field = field;
    }


}
