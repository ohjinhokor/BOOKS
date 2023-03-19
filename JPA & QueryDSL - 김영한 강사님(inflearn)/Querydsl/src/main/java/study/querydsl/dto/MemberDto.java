package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private String username;
    private int age;

    @QueryProjection // 중급 문법 - 프로젝션과 결과 반환(@QueryProjection)에서 사용. 어노테이션을 붙인 뒤 compileQuerydsl을 실행시켜줘야함
    public MemberDto(String username, int age){
        this.username = username;
        this.age = age;
    }
}
