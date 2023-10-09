package com.example.demo.domain.issue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import java.util.List;
//Mybatisのアノテーション
// @MapperによりMybatisを通してデータベースに接続できるようになる
//MYBatisの場合は@Mapperをつけていればbean登録されていると思ってよい
@Mapper
public interface IssueRepository {
    //このメソッド（findAll())でどのようなSQLを発行するか記述
    //文字列を引数にとり、実行したいSQLを渡すことができる
    //現在findAll()にはリストが入っているので（IssueServiceで入れた）
    // それに対して全部持ってくるよというSQLを実行している
    @Select("select * from issues")
    List<IssueEntity> findAll();


    @Insert("insert into issues (summary, description) values(#{summary}, #{description})")
    void insert(String summary, String description);

    @Select("select * from issues where id = #{issueId}")
    IssueEntity findById(long issueId);
}
