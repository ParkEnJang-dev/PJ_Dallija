package com.spring.dallija.repository;

import com.spring.dallija.domain.item.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl {

    private final EntityManager em;

    public void save(Items item) {
        em.persist(item);
    }

    public Items findOne(Long id) {
        return em.find(Items.class, id);
    }

    public List<Items> findAll() {
        return em.createQuery("select i from Items i", Items.class)
                .getResultList();
    }

}
