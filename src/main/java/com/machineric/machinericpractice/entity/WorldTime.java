package com.machineric.machinericpractice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorldTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @UpdateTimestamp
    private LocalDateTime lastUpdateTime;
}
