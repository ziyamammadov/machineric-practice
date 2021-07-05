package com.machineric.machinericpractice.client;

import com.machineric.machinericpractice.client.dto.WorldTimeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${openfeign.clients.worldtime.name}", url = "${openfeign.clients.worldtime.url}", configuration = FeignConfig.class)
public interface WorldTimeClient {

    @RequestMapping(method = RequestMethod.GET)
    WorldTimeResponse getCurrentWorldTime();
}
