//package project.board.dto;
//
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Getter
//@Builder
//@AllArgsConstructor //@Builder와 @AllArgsConstructor은 같이 써야함
//@NoArgsConstructor
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//public class PageResponseDto {
//
//    private List<BoardResponseDto> content;
//    private int pageNum;
//    private int pageSize; // 페이징 개수
//    private Long totalElements;
//    private int totalPages;
//    private boolean last;
//
//}
