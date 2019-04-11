package com.xabe.date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTest.class);

    @Test
    public void shouldGetStartRamadan() throws Exception {
        final HijrahDate hijrahDate = HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1).with(ChronoField.MONTH_OF_YEAR, 9);
        final LocalDate date = LocalDate.now();
        log(date, hijrahDate);
        log(date.minus(1l, ChronoUnit.YEARS), hijrahDate.minus(1, ChronoUnit.YEARS));

    }

    private void log(LocalDate localDate, HijrahDate ramadan) {
        LOGGER.info("--- Ramandan ---");
        LOGGER.info("Start : " + localDate.from(ramadan));
        LOGGER.info("End : " + localDate.from(ramadan.with(TemporalAdjusters.lastDayOfMonth())));
        LOGGER.info("End : " + localDate.from(ramadan.plus(ramadan.lengthOfMonth() - 1, ChronoUnit.DAYS)));
        final Period between = Period.between(localDate.from(ramadan), localDate.from(ramadan.with(TemporalAdjusters.lastDayOfMonth())));
        LOGGER.info("Number days : " + between.getDays());
    }

    @Test
    public void givenAStringWhenInvokeParseThenReturnLocalDate() throws Exception {
        //Given
        final String value = "2019-01-01";
        final String value2 = "01-01-2019";
        final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("[yyyy-MM-dd]")
                .appendPattern("[dd-MM-yyyy]")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        //When
        final LocalDate localDate = LocalDate.parse(value, dateTimeFormatter);
        final LocalDate localDate2 = LocalDate.parse(value2, dateTimeFormatter);

        //Then
        assertThat(localDate, is(notNullValue()));
        assertThat(localDate, is(notNullValue()));
        assertThat(localDate2, is(localDate));
    }

    @Test
    public void givenAStringWhenInvokeParseThenReturnYearMonth() throws Exception {
        //Given
        final String value = "01/19";
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yy");

        //When
        final YearMonth yearMonth = YearMonth.parse(value, dateTimeFormatter);

        //Then
        assertThat(yearMonth, is(notNullValue()));
    }

    @Test
    public void givenALocalTimeWhenInvokeGetStatusThenReturnStatus() throws Exception {
        //Given
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        final LocalTime localTime = LocalTime.of(03,00);
        final LocalTime start = LocalTime.parse("00:00",timeFormatter);
        final LocalTime end = LocalTime.parse("09:00",timeFormatter);

        //When
        final boolean result = localTime.isAfter(start) && localTime.isBefore(end);

        //Then
        assertThat(result, is(true));
    }

    @Test
    public void givenATwoLocalDateWhenInvokeBetweenThenReturnNumberMonth() throws Exception {
        //Given
        final LocalDate start = LocalDate.of(2019, Month.JANUARY,1);
        final LocalDate end = LocalDate.of(2019,Month.AUGUST,30);


        //When
        final long result = Period.between(start, end).toTotalMonths() + 1;

        //Then
        assertThat(result,is(8l));
    }

    @Test
    public void givenATwoLocalDateWhenInvokeBetweenThenReturnNumberDay() throws Exception {
        //Given
        final LocalDate start = LocalDate.of(2019, Month.JANUARY,1);
        final LocalDate end = LocalDate.of(2019,Month.JANUARY,30);


        //When
        final long result = ChronoUnit.DAYS.between(start, end) + 1;

        //Then
        assertThat(result,is(30l));
    }

    @Test
    public void givenALocalTimeWhenInvokeGetStatusThenReturnSeconds() throws Exception {
        //Given
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        final LocalTime start = LocalTime.parse("10:00:00",timeFormatter);
        final LocalTime end = LocalTime.parse("12:00:00",timeFormatter);

        //When
        final int result = Math.toIntExact(Duration.between(start,end).getSeconds());

        //Then
        assertThat(result, is(7200));
    }


    @Test
    public void shouldGetStringDateWhenInvokeFormat() throws Exception {
        //Given
        final ZonedDateTime zonedDateTime = ZonedDateTime.now();
        final OffsetDateTime offsetDateTime = OffsetDateTime.now();
        final Instant instant = Instant.now();


        //When
        final String zonaDateTimeformat = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        final String offsetTmeformat = offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        final String instantformat = DateTimeFormatter.ISO_INSTANT.format(instant);

        //Then
        LOGGER.info(String.format("ZonedDateTime %s",zonaDateTimeformat));
        LOGGER.info(String.format("OffsetDateTime %s",offsetTmeformat));
        LOGGER.info(String.format("Instant %s",instantformat));
        assertThat(zonaDateTimeformat, is(notNullValue()));
        assertThat(offsetTmeformat, is(notNullValue()));
        assertThat(instantformat, is(notNullValue()));
    }

}

