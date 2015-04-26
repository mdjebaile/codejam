package iteration;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This implementation is part of Atlassian interview screenings.
 * Test class for MyFolder
 * 
 * @author miguel.djebaile
 */
public class MyFolderTest {

    // Object to be tested
    @InjectMocks
    MyFolder<Integer, Integer> myFolder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void givenValidParams_fold_shouldExecuteSuccessfully() {
        // Prepare mocks
        Function2 functionMock = mock(Function2.class);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        // When
        myFolder.fold(0, queue, functionMock);
        // Then
        verify(functionMock, times(3)).apply(any(), any());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void givenValidParamsAndEmptyQueue_fold_shouldExecuteSuccessfully() {
        // Prepare mocks
        Function2 functionMock = mock(Function2.class);
        Queue queueMock = mock(Queue.class);
        // Given
        given(queueMock.isEmpty()).willReturn(true);
        // When
        myFolder.fold(0, queueMock, functionMock);
        // Then
        verify(functionMock, never()).apply(any(), any());
    }

    @Test
    public void givenValidParams_fold_shouldReturnFold() {
        // Prepare mocks
        Function2<Integer, Integer, Integer> function = new Function2<Integer, Integer, Integer>() {
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        // When
        int actual = (Integer) myFolder.fold(0, queue, function);
        // Then
        Assert.assertEquals(6, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidParams_fold_shouldThrowIllegalArgumentException() {
        // When
        myFolder.fold(null, null, null);
    }
}
