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

    public Goods saveGood(String title, long sellerId, int cost, int count, String description, String categories) {
        return goodsRepository.save(new Goods(title, sellerId, cost, count, description, categories));
    }

    public Optional<Goods> findById(long id) {
        return goodsRepository.findById(id);
    }

    public int decreaseStock(long id, int count) {return goodsRepository.decreaseCount(id, count);}

    public List<Goods> findDistinctByCategoriesIn(Set<String> categories) {return goodsRepository.findDistinctByCategoriesIn(categories);}
}
