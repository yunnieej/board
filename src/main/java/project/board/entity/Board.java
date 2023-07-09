package project.board.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String writer;

    @NotNull
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @ColumnDefault("0")
    private int hits;

    @Builder
    public Board(Long id, String writer, String title, String content, int hits){
        this.id  = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.hits = hits;
    }

}
