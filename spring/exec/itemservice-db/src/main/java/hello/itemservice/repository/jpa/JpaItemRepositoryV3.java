package hello.itemservice.repository.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static hello.itemservice.domain.QItem.item;

@Repository
@Transactional

public class JpaItemRepositoryV3 implements ItemRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepositoryV3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class,itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setPrice(updateParam.getPrice());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(em.find(Item.class,id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName=cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();


        List<Item> result = query
                .select(item)
                .from(item)
                .where(itemNameLike(itemName),priceLoe(maxPrice))
                .fetch();
        return result;
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
