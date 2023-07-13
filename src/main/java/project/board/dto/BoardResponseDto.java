package project.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.board.entity.Board;

@NoArgsConstructor
@Getter
@Setter
public class BoardResponseDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String writer;
    @JsonProperty
    private int hits;
    private String content;

    @Builder
    public BoardResponseDto(Long id, String title, String writer, String content ,int hits){
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.hits = hits;
        this.content = content;
    }

}
