package project.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BoardUpdateDto {

//    @JsonProperty
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

//    @JsonProperty
    @Pattern(regexp="[0-9a-zA-Z가-힣]*",message="내용에 특수문자는 입력할 수 없습니다.")
    private String content;

}
