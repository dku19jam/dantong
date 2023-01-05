package com.jaeun.dantong.service;

import com.jaeun.dantong.domain.dto.request.CreateBoardRequest;
import com.jaeun.dantong.domain.entity.Board;
import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.repository.BoardRepository;
import com.jaeun.dantong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Transactional
    public Long createBoard(String email, CreateBoardRequest createBoardRequest) {
        User user = getUserByEmail(email);
        Board board = boardRepository
                .save(new Board(user, createBoardRequest.getTitle(), createBoardRequest.getContent()));
        user.getBoards().add(board);
        return board.getId();
    }

    @Transactional
    public Long deleteBoard(String email, Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        Long id = board.get().getId();
//        assert board.isPresent();
        boardRepository.delete(board.get());
        User user = getUserByEmail(email);
        user.getBoards().remove(board.get());

        return id;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

}
