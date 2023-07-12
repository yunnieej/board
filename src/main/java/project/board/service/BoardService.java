package project.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.board.dto.BoardRequestDto;
import project.board.dto.BoardResponseDto;
import project.board.dto.BoardUpdateDto;
import project.board.entity.Board;
import project.board.repository.BoardRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 저장
    @Transactional
    public Long save(BoardRequestDto boardRequestDto){

//        if(boardRequestDto.getWriter().isBlank() && boardRequestDto.getTitle().isBlank()){
//            throw new IllegalStateException("필수 입력값 (작성자, 제목)값이 없습니다.");
//        }
//        else if(boardRequestDto.getContent().matches("[0-9a-zA-Z가-힣]*")){
//            throw new IllegalStateException("내용에는 특수문자가 들어갈 수 없습니다.");
//        }
        Long savedId = boardRepository.save(boardRequestDto.toEntity()).getId();
        return savedId;
    }


    @Transactional
    public BoardResponseDto findById(Long id){
        Board board = boardRepository.findById(id).get();
        board.updateHits();
        BoardResponseDto boardResponseDto = BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .writer(board.getWriter())
                .hits(board.getHits())
                .build();
        return boardResponseDto;

    }

    @Transactional
    public List<BoardResponseDto> findAll(){
        List<Board> all = boardRepository.findAll();
        return getBoardResponseDto(all);
    }

    // 해당 작성자로 게시판 조회
    @Transactional
    public List<BoardResponseDto> findByWriter(String writer){
        List<Board> getEntityList = boardRepository.findByWriter(writer);
        return getBoardResponseDto(getEntityList);
    }

    // 해당 제목으로 게시판 조회
    @Transactional
    public List<BoardResponseDto> findByTitle(String title){
        List<Board> getEntityList = boardRepository.findByTitle(title);
        return getBoardResponseDto(getEntityList);
    }

    private List<BoardResponseDto> getBoardResponseDto(List<Board> getEntityList) {
        List<BoardResponseDto> getDtoList = new ArrayList<>();

        for(Board board : getEntityList){
            BoardResponseDto boardResponseDto = BoardResponseDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .writer(board.getWriter())
//                    .hits(boardRepository.updateHits(board.getId()))
                    .hits(board.getHits())
                    .build();

            getDtoList.add(boardResponseDto);
        }
        return getDtoList;
    }

    // 게시글 수정
    @Transactional
    public Long update(BoardUpdateDto boardUpdateDto, Long id){

        if(boardRepository.findById(id).isEmpty()){
            throw new NoSuchElementException("해당 순번이 존재하지 않습니다.");
        }

        Board board = boardRepository.findById(id).get();
        board.update(boardUpdateDto);
        return id;
    }

    // 게시글 삭제
    @Transactional
    public Long delete(Long id){

//        if (boardRepository.findById(id).isEmpty()){
//            throw new NoSuchElementException("해당 순번이 존재하지 않습니다.");
//        }
        boardRepository.deleteById(id);
        return id;
    }

}













