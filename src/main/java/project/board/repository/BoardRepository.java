package project.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.board.entity.Board;

/**
 * JpaRepository<Entity 클래스, pk 타입> 으로 만들면
 * Board가 데이터베이스에 접근할 수 있도록 하는 Repository가 된다.
 *
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
