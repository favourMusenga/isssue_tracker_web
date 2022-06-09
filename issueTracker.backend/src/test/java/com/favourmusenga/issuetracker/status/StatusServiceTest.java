package com.favourmusenga.issuetracker.status;

import com.favourmusenga.issuetracker.shared.exceptions.CustomBadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StatusServiceTest {

    @Mock
    StatusRepository statusRepository;

    StatusService underTest;

    @BeforeEach
    void setUp(){
        underTest = new StatusService(statusRepository);
    }

    @Test
    @DisplayName("save New Status")
    void shouldBeAbleToSaveNewStatus() throws CustomBadRequestException {
        Status mockStatus = new Status("working", "check if machine is working");

        given(statusRepository.doesStatusExists(anyString())).willReturn(false);
        underTest.saveNewStatus(mockStatus);

        ArgumentCaptor<String> statusNameArgument = ArgumentCaptor.forClass(String.class);
        verify(statusRepository).doesStatusExists(statusNameArgument.capture());

        String statusNameCaptured = statusNameArgument.getValue();

        assertThat(statusNameCaptured).isEqualTo(mockStatus.getName());

    }

    @Test
    @DisplayName("not save New Status")
    void shouldNotBeAbleToSaveNewStatusButThrowAnError(){
        Status mockStatus = new Status("working", "check if machine is working");

        given(statusRepository.doesStatusExists(anyString())).willReturn(true);

        assertThatThrownBy(()->underTest.saveNewStatus(mockStatus))
                .isInstanceOf(CustomBadRequestException.class)
                .hasMessageContaining("status already exist");

        verify(statusRepository, never()).save(mockStatus);

    }

    @Test
    @DisplayName("get All Status")
    void shouldGetAllStatus() {
        underTest.getAllStatus();

        verify(statusRepository).findAll();
    }

    @Test
    @DisplayName("get Status By Id")
    void shouldGetStatusById() {
        Long mockId = 1L;
        Status mockStatus = new Status("working", "check if machine is working");

        given(statusRepository.findById(anyLong())).willReturn(Optional.of(mockStatus));

        underTest.getStatusById(mockId);

        verify(statusRepository).findById(mockId);
    }

    @Test
    @DisplayName("get not Status By Id")
    void shouldNotGetStatusById() {
        Long mockId = 1L;
        assertThatThrownBy(()->underTest.getStatusById(mockId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No value present");
    }

}




