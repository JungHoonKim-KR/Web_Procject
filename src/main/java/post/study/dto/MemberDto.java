package post.study.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class MemberDto {
    private Long id;
    private String emailId;
    private String username;
    private String password;
    private String position;

    @Builder
    public MemberDto(Long id,String emailId, String username, String password, String position) {
        this.id=id;
        this.emailId = emailId;
        this.username = username;
        this.password = password;
        this.position = position;

    }
}
