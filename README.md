# changebook-elasticsearch-sample
### Elastic Search Sample

#### 测试
```
// 通过“性别”和“年龄”，查询列表，正序
UserRepositoryTests::testFindByGenderAndAgeOrderBySortAsc();

// 通过“性别”和“年龄”，查询列表，倒序
UserRepositoryTests::testFindByGenderAndAgeOrderBySortDesc();

// 通过“等级”，查询列表，Nested
UserRepositoryTests::testRank();

// 保存
UserRepositoryTests::testSave();

// 删除
UserRepositoryTests::testDelete();
```
