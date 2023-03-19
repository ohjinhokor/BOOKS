package study.datajpa.dto;

import lombok.Data;

@Data // 엔티티에는 @Data 어노테이션을 dto에 잘 붙이지 않는다. 예제니까 사용
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
