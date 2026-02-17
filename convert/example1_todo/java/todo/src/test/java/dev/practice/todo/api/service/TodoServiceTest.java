package dev.practice.todo.api.service;

import dev.practice.todo.domain.Todo;
import dev.practice.todo.domain.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class) // 프로젝트에서 개발한 애들을 띄우지는 않고.. 스프링 기본 컨테이너를 띄우는듯, (service 직접 생성함)
public class TodoServiceTest {

    @MockBean
    private TodoRepository repository;

    private TodoService service;

    private Todo stub;

    @BeforeEach
    public void setUp() {
        service = new TodoService(repository);
        stub = todoStub();
    }

    @Test
    public void 한개의_TODO를_반환해야한다() {
        // Given
        given(repository.findById(1L)).willReturn(Optional.of(stub));

        // When
        Todo actual = service.getTodo(1L);

        // Then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(stub);
    }


    public Todo todoStub() {
        return Todo.builder()
                .id(1L)
                .title("테스트")
                .description("테스트 상세")
                .done(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();
    }


}