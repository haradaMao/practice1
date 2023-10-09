package com.example.demo.domain.issue;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
//以下をつけることでBean登録完了（インジェクションのため①）
@Service
//issueRepositoryのインスタンスが作成される
@RequiredArgsConstructor
public class IssueService {
    // IssueServiceの中でRepositoryを呼び出したい
    // 初期化時に一度だけ代入されるのみ、その後再代入されないことを強制するため
    private final IssueRepository issueRepository;
    //帰り値はIssueEntity型（クラス）のList
    //IssueService.fidAll()が実行されたとき、issueRepository.findAll()が返される
    //つまり、S→R→Sとなっている
    public List<IssueEntity> findAll(){
        //(インスタンス化して初期化している)
        //id:等は自動で入力されているため、入力する必要はない
        //今ここで一つのリストに複数のIssueEntity（データ）を入れている
        //List.of（）は不変のListを返す。後での値追加無理
        return issueRepository.findAll();
    }

    // トランザクションをメソッドに張っている
    //エラーが発生したりした場合、処理を反映（コミット）しない
    @Transactional
    public void create(String summary, String description){
        issueRepository.insert(summary, description);
    }

    public IssueEntity findById(long issueId){
        return issueRepository.findById(issueId);
    }


    
}
