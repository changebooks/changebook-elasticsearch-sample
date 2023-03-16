package io.github.changebooks.elasticsearch.sample;

import io.github.changebooks.elasticsearch.EsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 用户数仓
 *
 * @author changebooks@qq.com
 */
@Component
public interface UserRepository extends EsRepository<Long, User> {

    /**
     * 通过“性别”和“年龄”，查询列表
     *
     * @param gender   性别
     * @param age      年龄
     * @param pageable 分页参数
     * @return 用户列表
     */
    Page<User> findByGenderAndAgeOrderBySortAsc(Integer gender, Integer age, Pageable pageable);

}
