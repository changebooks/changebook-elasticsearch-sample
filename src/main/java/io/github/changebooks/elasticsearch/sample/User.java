package io.github.changebooks.elasticsearch.sample;

import io.github.changebooks.elasticsearch.EsEntity;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * 用户实例
 *
 * @author changebooks@qq.com
 */
@Document(indexName = "user", createIndex = true)
public class User extends EsEntity<Long> {
    /**
     * 用户名
     */
    @Field(type = FieldType.Keyword)
    private String username;

    /**
     * 性别
     */
    @Field(type = FieldType.Integer)
    private Integer gender;

    /**
     * 年龄
     */
    @Field(type = FieldType.Integer)
    private Integer age;

    /**
     * 等级列表
     */
    @Field(type = FieldType.Nested)
    private List<Rank> ranks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }

}
