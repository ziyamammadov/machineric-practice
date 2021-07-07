package groovy.com.machineric.machinericpractice.controller


import com.machineric.machinericpractice.controller.WorldTimeController
import com.machineric.machinericpractice.service.WorldTimeService
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class WorldTimeControllerTest extends Specification {
    MockMvc mockMvc
    def worldTimeController
    def worldTimeService
    def enhancedRandom

    void setup() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom()
        worldTimeService = Mock(WorldTimeService) as WorldTimeService
        worldTimeController = new WorldTimeController(worldTimeService)
        mockMvc = standaloneSetup(worldTimeController).build()
    }

    def "get method should return the latest polled world time with the status 200 OK"() {

        when:"sends get request to below endpoint"
        def result = mockMvc.perform(get("/worldtime")
                .contentType(MediaType.APPLICATION_JSON))

        then:"result should be OK "
        result.andExpect(status().isOk())

        1 * worldTimeService.get()
        0 * _._
    }
}
