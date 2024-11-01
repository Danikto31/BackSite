package org.example.backbase.Repository;

import org.example.backbase.Entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    @Modifying
    @Query("update Goods g set g.count = g.count - ?2 where g.id = ?1")
    int decreaseCount(long id, int count);

    List<Goods> findDistinctByCategoriesIn(Set<String> categories);
}
