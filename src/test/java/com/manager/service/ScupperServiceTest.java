package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Scupper;
import com.manager.model.Users;
import com.manager.repository.ScupperRepository;
import com.manager.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ScupperServiceTest {

    private static final String USER_ID = "1";
    private static final String PROJECT_ID = "2";
    private static final String SCUPPER_ID = "3";

    private static final Users USER = new Users("Jan", "Kowalski", "sample@gmail.com", "qwerty");
    private static final Project PROJECT = new Project("Sample project", "Sample project description", null);
    private static final Scupper SCUPPER = new Scupper("Sample Building", 2123.0,  70.0, 10.0, 700.0, 350.0, 10.0, 5.0, 3.64, 4.0, null);

    private static final Users USER_WITH_ID = new Users("Jan", "Kowalski", "sample@gmail.com", "qwerty");
    private static final Project PROJECT_WITH_ID_AND_USER = new Project("Sample project", "Sample project description", USER_WITH_ID);
    private static final Scupper SCUPPER_WITH_ID_AND_PROJECT = new Scupper("Sample Building", 2123.0,  70.0, 10.0, 700.0, 350.0, 10.0, 5.0, 3.64, 4.0, PROJECT_WITH_ID_AND_USER);

    @Before
    public void setUp() {
        USER_WITH_ID.setId(Integer.parseInt(USER_ID));

        PROJECT_WITH_ID_AND_USER.setId(Integer.parseInt(PROJECT_ID));
        PROJECT_WITH_ID_AND_USER.setUser(USER);

        SCUPPER_WITH_ID_AND_PROJECT.setId(Integer.parseInt(SCUPPER_ID));
        SCUPPER_WITH_ID_AND_PROJECT.setProject(PROJECT_WITH_ID_AND_USER);
    }

    @SpyBean
    private ScupperService scupperService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ScupperRepository scupperRepository;

    @Test
    public void shouldCreateScuppersForParticularUserAndUserProject() {
//        // when
//        when(scupperRepository.save(any())).thenReturn(SCUPPER_WITH_ID_AND_PROJECT);
//
//        Scupper actual = scupperService.addScupper(USER_ID, PROJECT_ID, SCUPPER);
//
//        // then
//        assertEquals(SCUPPER_WITH_ID_AND_PROJECT, actual);

    }


    @ParameterizedTest
    @MethodSource("provideDoubleNumbers")
    public void shouldReturnRoundedNumberNotLessThanTwo(double input, double expected) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // when
        Method method = ScupperService.class.getDeclaredMethod("ceilNumberOfScuppers", Double.class);
        method.setAccessible(true);
        Double actual = (double) method.invoke(scupperService, input);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideDoubleNumbers() {
        return Stream.of(
                Arguments.of(2.33, 3.0),
                Arguments.of(0.1, 2.0),
                Arguments.of(0.99, 2.0),
                Arguments.of(0.5, 2.0),
                Arguments.of(2.5, 3.0),
                Arguments.of(2.0000000000005, 3.0),
                Arguments.of(0.0000000000005, 2.0)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentException() throws NoSuchMethodException {
        // given
        Method method = ScupperService.class.getDeclaredMethod("ceilNumberOfScuppers", Double.class);
        method.setAccessible(true);

        // then
        org.assertj.core.api.Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> method.invoke(scupperService, new Object[] {}));

    }

    @ParameterizedTest
    @MethodSource("provideNumberWithManyDecimalPlaces")
    public void shouldRoundNumberToTwoPlaces(double input, double expected) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // when
        Method method = ScupperService.class.getDeclaredMethod("roundToTwoDecimalPlaces", Double.class);
        method.setAccessible(true);
        Double actual = (double) method.invoke(scupperService, input);

        // then
        assertEquals(expected, actual);

    }

    private static Stream<Arguments> provideNumberWithManyDecimalPlaces() {
        return Stream.of(
                Arguments.of(2.3234, 2.32),
                Arguments.of(2.000000005, 2.00),
                Arguments.of(0.11, 0.11),
                Arguments.of(0.222, 0.22),
                Arguments.of(777.1, 777.10),
                Arguments.of(-1.0, 0)
        );

    }

    @Test
    public void shouldThrowIllegalArgumentException_RoundNumberToTwoPlaces() throws NoSuchMethodException {
        Method method = ScupperService.class.getDeclaredMethod("roundToTwoDecimalPlaces", Double.class);
        method.setAccessible(true);

        org.assertj.core.api.Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> method.invoke(scupperService, new Object[] {}));


    }

    @ParameterizedTest
    @ValueSource(strings = {"q", "qwsd", "", "   ", "%", "#$^", "0"})
    public void ShouldReturnZeroWhenPassIncorrectNumber(String input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // when
        Method method = ScupperService.class.getDeclaredMethod("checkIfDoubleCorrectAndConvert", String.class);
        method.setAccessible(true);
        Double actual = (double) method.invoke(scupperService, input);

        // then
        assertEquals(0d, actual);

    }

    @ParameterizedTest
    @MethodSource("provideNumbersForConvertingToString")
    public void ShouldConvertStringToDouble(String input, double expected) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // when
        Method method = ScupperService.class.getDeclaredMethod("checkIfDoubleCorrectAndConvert", String.class);
        method.setAccessible(true);
        Double actual = (double) method.invoke(scupperService, input);

        // then
        assertEquals(expected, actual);

    }

    private static Stream<Arguments> provideNumbersForConvertingToString() {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("2.0", 2.0),
                Arguments.of("333.333", 333.333),
                Arguments.of("2.5545", 2.5545),
                Arguments.of("-1", 0),
                Arguments.of("-20.98", 0)
        );

    }
}