package project.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.board.dto.BoardResponseDto;
import project.board.entity.Board;

import java.util.List;

/**
 * JpaRepository<Entity 클래스, pk 타입> 으로 만들면
 * Board가 데이터베이스에 접근할 수 있도록 하는 Repository가 된다. -> entity는 바뀌지 않음
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 작성자로 찾기
    List<Board> findByWriter(String writer);
    // 제목으로 찾기
    List<Board> findByTitle(String title);

//    List<Board> findByPage(Pageable pageable);

    // 조회수 올리기
//    @Modifying
//    @Query(value = "update Board b set b.hits = b.hits+1 where b.id=:id")
//    int updateHits(@Param("id") Long id);

}
