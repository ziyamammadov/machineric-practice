package com.machineric.machinericpractice.client.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WorldTimeResponse {
    private String abbreviation;
    private String clientIp;
    private ZonedDateTime datetime;
    private Integer dayOfWeek;
    private Integer dayOfYear;
    private Boolean dst;
    private String dstFrom;
    private Integer dstOffset;
    private String dstUntil;
    private BigInteger rawOffset;
    private String timezone;
    private BigInteger unixtime;
    private ZonedDateTime utcDatetime;
    private String utcOffset;
    private Integer weekNumber;
}
