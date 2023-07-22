package post.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Language_Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Language_Member(String language) {
        this.language = language;
    }

    public Language_Member() {

    }




}
