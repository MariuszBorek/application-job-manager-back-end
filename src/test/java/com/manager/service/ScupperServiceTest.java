package com.manager.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ScupperServiceTest {

    @Autowired
    private ScupperService scupperService;


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