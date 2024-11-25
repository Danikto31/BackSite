package org.example.backbase.Services;

import org.example.backbase.Entity.Goods;
import org.example.backbase.Repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    public long saveGood(Goods goods) {
        return goodsRepository.save(goods).getId();
    }

    public Optional<Goods> findById(long id) {
        return goodsRepository.findById(id);
    }

    public int decreaseStock(long id, int count) {return goodsRepository.decreaseCount(id, count);}

    public List<Goods> findByTitleAndCategories(String title, Set<String> categories) {return goodsRepository.findDistinctByTitleLikeIgnoreCaseAndCategoriesIn(title, categories);}

    public List<Goods> findByTitle(String title) {return goodsRepository.findDistinctByTitleLikeIgnoreCase(title);}

    public List<Goods> findByCategories(Set<String> categories) {return goodsRepository.findDistinctByCategoriesIn(categories);}

    public void deleteById(long id) {goodsRepository.deleteById(id);}
}
