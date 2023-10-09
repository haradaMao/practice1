package com.example.demo.web.issue;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.domain.issue.IssueService;

import lombok.RequiredArgsConstructor;
@Controller
//@RequestMapping("/issues")このしたではGetMapping（）内の/issuesを省略することができる
@RequestMapping("/issues")
//finalがついていてかつ初期化されていないフィールドを初期化するコンストラクタを自動生成
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;
    //Get /issues
    //RequestMappingのおかげで("/issues")は省略
    @GetMapping
    //Java上のデータをタイムリーフに渡すときは、springが提供するModelというクラスを使う
    //タイムリーフはJava上のデータとテンプレ（HTML？）を合成して表示してくれる
    //Serviceで(issueEntityを複数個初期化→内容代入下リストを作成)→タイムリーフに渡す
    public String showList(Model model){
        //tributeでタイムリーフにオブジェクトを渡す
        //model.addAttribute(タイムリーフで参照するときのキー,タイムリーフに渡したいオブジェクト)
        //キーは画面から受け取るキーのこと（isseListはlist.htmlで使われている）
        //HTMLで”issueList”を指定すると値が表示できる
        //中身を見るとこれでModelに値（リスト）が渡っている→タイムリーフにわたる
        model.addAttribute("issueList", issueService.findAll());
        return "issues/List";
    }
    // GETを使うと渡したものがURLで丸見えに
    //Get /issues/creationForm
    //RequestMappingのおかげで("/issues")は省略
    @GetMapping("/creationForm")
    // public String showCreationForm(Model model){
    //     model.attribute("issueForm", new IssueForm());
    //     return "issues/creationForm";
    // }
    // @ModelAttribute を使うと、上を下のように簡潔に書くことができる
    //IssueFormがModelの中に入れられる
    public String showCreationForm(@ModelAttribute IssueForm form){
        // return文でのテンプレートの指定はresources/templatesからの相対パス
        return "issues/creationForm";
    }
    // POSTを使うと渡したものが丸見えにならない
    // POST /issues
    @PostMapping
    //@Validated を使うと、IssueFormで設定した入力文字の条件が結びつく
    //BindingResult bindingResultが検証をしてくれる
    public String create(@Validated IssueForm form, BindingResult bindingResult,Model model){
        //バリデーションチェック
        //条件を満たしていない場合は、作成フォームに戻す
        if(bindingResult.hasErrors()){
            return showCreationForm(form);
        }

        //データの永続化
        issueService.create(form.getSummary(), form.getDescription());
        //リロードボタン対策 redirect:/をつける
        return "redirect:/issues"; 
    }

    //課題詳細画面
    //GET localhost:8080/issues/1←この数字は適宜変えたい
    @GetMapping("/{issueId}")
    // @PathVariableとはURLに含まれた文字列をパラメータとして受け取るためのアノテーション
    public String showDatail(@PathVariable("issueId") long issueId, Model model){
        model.addAttribute("issue", issueService.findById(issueId));
        return "issues/detail";
    }

    
    
}
