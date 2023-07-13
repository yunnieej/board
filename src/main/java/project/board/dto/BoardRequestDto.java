package project.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.board.entity.Board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class BoardRequestDto {

    @NotBlank(message = "작성자는 필수 입력 항목입니다.")
    private String writer;

    @JsonProperty
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String title;

    @JsonProperty
    @Pattern(regexp="[ ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]+",message="내용에 특수문자는 입력할 수 없습니다.")
    private String content;

    private int hits;

    @Builder
    public BoardRequestDto(String writer, String title, String content){
        this.writer = writer;
        this.title = title;
        this.content = content;

    }

    public Board toEntity(){
        return Board.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .hits(0)
                .build();
    }
}
