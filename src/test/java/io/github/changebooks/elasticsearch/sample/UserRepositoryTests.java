package io.github.changebooks.elasticsearch.sample;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"io.github.changebooks.elasticsearch.sample"})
public class UserRepositoryTests {

    private static final IndexCoordinates INDEX_COORDINATES = IndexCoordinates.of("user");

    @Resource
    private UserRepository userRepository;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testFindByGenderAndAgeOrderBySortAsc() {
        Pageable pageable = PageRequest.of(0, 2);

        Page<User> list = userRepository.findByGenderAndAgeOrderBySortAsc(1, 22, pageable);
        list.get().forEach(System.out::println);
    }

    @Test
    public void testFindByGenderAndAgeOrderBySortDesc() {
        Pageable pageable = PageRequest.of(0, 2);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.
                boolQuery().
                filter(QueryBuilders.termQuery("gender", 1)).
                filter(QueryBuilders.termQuery("age", 22));

        FieldSortBuilder fieldSortBuilder = SortBuilders.
                fieldSort("sort").
                order(SortOrder.DESC);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(boolQueryBuilder).
                withSorts(fieldSortBuilder).
                withPageable(pageable).
                build();

        SearchHits<User> searchHits = elasticsearchOperations.search(searchQuery, User.class, INDEX_COORDINATES);
        searchHits.get().forEach(System.out::println);
    }

    @Test
    public void testRank() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.
                boolQuery().
                filter(QueryBuilders.nestedQuery("ranks", QueryBuilders.termQuery("ranks.channel", 0), ScoreMode.None)).
                filter(QueryBuilders.nestedQuery("ranks", QueryBuilders.termQuery("ranks.num", 1), ScoreMode.None));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(boolQueryBuilder).
                build();

        SearchHits<User> searchHits = elasticsearchOperations.search(searchQuery, User.class, INDEX_COORDINATES);
        searchHits.get().forEach(System.out::println);
    }

    @Test
    public void testSave() {
        List<User> list = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            User user = new User();

            user.setId((long) i);
            user.setUsername("用户名-" + i);
            user.setGender(i % 2 + 1);
            user.setAge((new Random()).nextInt(3) + 20);
            user.setSort(i);
            user.setRemark("备注-" + i);
            user.setNote("内部备注-" + i);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            List<Rank> ranks = new ArrayList<>();

            Rank rank1 = new Rank();
            rank1.setChannel((new Random()).nextInt(2));
            rank1.setNum((new Random()).nextInt(2));
            ranks.add(rank1);

            Rank rank2 = new Rank();
            rank2.setChannel((new Random()).nextInt(2) + 2);
            rank2.setNum((new Random()).nextInt(2) + 2);
            ranks.add(rank2);

            user.setRanks(ranks);

            list.add(user);
        }

        userRepository.saveAll(list);
    }

    @Test
    public void testDelete() {
        userRepository.deleteById(1L);
    }

}
