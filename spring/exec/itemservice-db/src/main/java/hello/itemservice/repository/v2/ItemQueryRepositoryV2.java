package hello.itemservice.repository.v2;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.itemservice.domain.QItem.item;


@Repository
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory query;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond){
        return query.select(item)
                .from(item)
                .where(itemNameLike(cond.getItemName()),priceLoe(cond.getMaxPrice()))
                .fetch();
    }
    private BooleanExpression itemNameLike(String itemNameCond) {
        if( itemNameCond==null || itemNameCond.equals("")) {
            return null;
        }
        return item.itemName.like("%"+itemNameCond+"%");
    }
    private BooleanExpression priceLoe(Integer priceCond) {
        if(priceCond==null) {
            return null;
        }
        return item.price.loe(priceCond);
    }
}
