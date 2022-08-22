package com.spring.dallija.repository;

import com.spring.dallija.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long Save(Member member){
        em.persist(member);
        //command 랑 query 랑 분리해라.
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
