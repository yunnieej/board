package project.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.board.dto.BoardRequestDto;
import project.board.entity.Board;
import project.board.repository.BoardRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardRequestDto boardRequestDto){
        return boardRepository.save(boardRequestDto.toEntity()).getId();
    }
}
