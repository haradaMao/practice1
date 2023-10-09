package com.example.demo.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    //ハンドラメソッドとリクエストを紐づける
    //GET /
    @GetMapping("/")
    //以下のやつハンドラーメソッド
    //このアノテーションによって戻り値のストリングがそのままレスポンスのコンテンツになる
    //@ResponseBody←今回いらなくなった
    public String index(){
        //index.htmlに飛ばす
        //拡張子なくてOKなのがSpringMVCの機能
        return "index";
    }
    
}
