package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

// 나머지 기능들 - Projections
public interface UsernameOnly {

    @Value("#{target.username + ' ' + target.age}")
    String getUsername();
}
