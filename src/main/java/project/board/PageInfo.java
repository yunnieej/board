package project.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
