package com.example.demo.web.issue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class IssueForm {
    
    // それぞれバリデーションを追加
    // 空白でない
    @NotBlank
    //文字数
    @Size(max=256)
    private String summary;

    @NotBlank
    @Size(max=256)
    private String description;
}

