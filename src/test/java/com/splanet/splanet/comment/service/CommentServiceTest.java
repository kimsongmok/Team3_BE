package com.splanet.splanet.comment.service;

import com.splanet.splanet.comment.dto.CommentRequest;
import com.splanet.splanet.comment.dto.CommentResponse;
import com.splanet.splanet.comment.entity.Comment;
import com.splanet.splanet.comment.repository.CommentRepository;
import com.splanet.splanet.core.exception.BusinessException;
<<<<<<< HEAD
import com.splanet.splanet.core.exception.ErrorCode;
=======
>>>>>>> weekly/11
import com.splanet.splanet.user.entity.User;
import com.splanet.splanet.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 댓글조회성공() {
        // given
        User user = User.builder().id(1L).nickname("user1").build();
        Comment comment = Comment.builder()
                .id(1L)
                .writer(user)
<<<<<<< HEAD
                .content("테스트 댓글")
=======
                .content("test content")
>>>>>>> weekly/11
                .user(user)
                .build();
        when(commentRepository.findByUserId(anyLong())).thenReturn(List.of(comment));

        // when
        List<CommentResponse> responses = commentService.getComments(1L);

        // then
        assertEquals(1, responses.size());
<<<<<<< HEAD
        assertEquals("테스트 댓글", responses.get(0).content());
=======
        assertEquals("test content", responses.get(0).content());
    }

    @Test
    void 댓글조회_사용자없음() {
        // given
        when(commentRepository.findByUserId(anyLong())).thenReturn(List.of());

        // when & then
        List<CommentResponse> responses = commentService.getComments(99L);
        assertEquals(0, responses.size());
>>>>>>> weekly/11
    }

    @Test
    void 댓글작성성공() {
        // given
        User user = User.builder().id(1L).build();
        User writer = User.builder().id(2L).build();
<<<<<<< HEAD
        CommentRequest request = new CommentRequest("테스트 댓글 내용");
=======
        CommentRequest request = new CommentRequest(1L, "test content");
>>>>>>> weekly/11
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.of(writer));
        when(commentRepository.save(any(Comment.class))).thenReturn(Comment.builder().id(1L).build());

        // when
<<<<<<< HEAD
        commentService.createComment(2L, 1L, request);
=======
        commentService.createComment(2L, request);
>>>>>>> weekly/11

        // then
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
<<<<<<< HEAD
    void 댓글작성_본인에게댓글달때_실패() {
        // given
        User user = User.builder().id(1L).build();
        CommentRequest request = new CommentRequest("자기 자신에게 댓글을 달려고 합니다.");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // when & then
        BusinessException exception = assertThrows(BusinessException.class, () -> commentService.createComment(1L, 1L, request));
        assertEquals(ErrorCode.INVALID_COMMENT_ID, exception.getErrorCode());
=======
    void 댓글작성_사용자없음() {
        // given
        CommentRequest request = new CommentRequest(1L, "test content");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(BusinessException.class, () -> commentService.createComment(2L, request));
>>>>>>> weekly/11
    }

    @Test
    void 댓글수정성공() {
        // given
        User writer = User.builder().id(2L).build();
<<<<<<< HEAD
        Comment comment = Comment.builder().id(1L).writer(writer).content("기존 내용").build();
        CommentRequest request = new CommentRequest("새로운 내용");
=======
        Comment comment = Comment.builder().id(1L).writer(writer).content("old content").build();
        CommentRequest request = new CommentRequest(1L, "new content");
>>>>>>> weekly/11
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when
        commentService.updateComment(1L, request, 2L);

        // then
<<<<<<< HEAD
        assertEquals("새로운 내용", comment.getContent());
    }

    @Test
    void 댓글수정_작성자불일치_실패() {
        // given
        User writer = User.builder().id(2L).build();
        Comment comment = Comment.builder().id(1L).writer(writer).content("기존 내용").build();
        CommentRequest request = new CommentRequest("새로운 내용");
=======
        assertEquals("new content", comment.getContent());
    }

    @Test
    void 댓글수정_작성자불일치() {
        // given
        User writer = User.builder().id(2L).build();
        Comment comment = Comment.builder().id(1L).writer(writer).content("old content").build();
        CommentRequest request = new CommentRequest(1L, "new content");
>>>>>>> weekly/11
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when & then
        assertThrows(BusinessException.class, () -> commentService.updateComment(1L, request, 3L));
    }

    @Test
    void 댓글삭제성공() {
        // given
        User writer = User.builder().id(2L).build();
        Comment comment = Comment.builder().id(1L).writer(writer).build();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when
        commentService.deleteComment(1L, 2L);

        // then
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
<<<<<<< HEAD
    void 댓글삭제_작성자불일치_실패() {
=======
    void 댓글삭제_작성자불일치() {
>>>>>>> weekly/11
        // given
        User writer = User.builder().id(2L).build();
        Comment comment = Comment.builder().id(1L).writer(writer).build();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when & then
        assertThrows(BusinessException.class, () -> commentService.deleteComment(1L, 3L));
    }
}