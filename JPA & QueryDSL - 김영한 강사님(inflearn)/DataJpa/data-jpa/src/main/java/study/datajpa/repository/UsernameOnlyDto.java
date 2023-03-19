package study.datajpa.repository;

// 나머지 기능들 - projections
public class UsernameOnlyDto {

    private final String username;

    public UsernameOnlyDto(String username){
        this.username = username;
    };

    public String getUsername() {
        return username;
    }
}
