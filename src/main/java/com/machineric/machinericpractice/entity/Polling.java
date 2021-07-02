package com.machineric.machinericpractice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Polling {
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


//    JSON example
//    {
//            "abbreviation":"+04",
//            "client_ip":"62.212.231.123",
//            "datetime":"2021-07-02T17:09:51.794012+04:00",
//            "day_of_week":5,
//            "day_of_year":183,
//            "dst":false,
//            "dst_from":null,
//            "dst_offset":0,
//            "dst_until":null,
//            "raw_offset":14400,
//            "timezone":"Asia/Baku",
//            "unixtime":1625231391,
//            "utc_datetime":"2021-07-02T13:09:51.794012+00:00",
//            "utc_offset":"+04:00",
//            "week_number":26
//    }
}
