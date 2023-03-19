package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);

    // 데이터와 카운트를 별도로 구하는 방법
   Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
   Page<MemberTeamDto> searchPageComplex2(MemberSearchCondition condition, Pageable pageable);
}
