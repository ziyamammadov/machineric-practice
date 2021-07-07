package groovy.com.machineric.machinericpractice.service

import com.machineric.machinericpractice.entity.WorldTime
import com.machineric.machinericpractice.exception.DataNotFoundException
import com.machineric.machinericpractice.exception.response.ResponseMessage
import com.machineric.machinericpractice.repository.WorldTimeRepository
import com.machineric.machinericpractice.service.impl.WorldTimeServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification

class WorldTimeServiceImplTest extends Specification {
    def worldTimeService
    def worldTimeRepository
    def enhancedRandom

    void setup() {
        enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandom()
        worldTimeRepository = Mock(WorldTimeRepository) as WorldTimeRepository
        worldTimeService = new WorldTimeServiceImpl(worldTimeRepository)
    }

    def "get method should return the WorldTime entity"() {
        given:
        def wt = enhancedRandom.nextObject(WorldTime, "id")
        def id = 1L
        wt.id = id
        def optionalWorldTime = Optional.of(wt)

        when:
        def worldTime = worldTimeService.get()

        then:
        worldTime == wt

        1 * worldTimeRepository.findById(id) >> optionalWorldTime
        0 * _._
    }

    def "get method should throw DataNotFoundException"() {
        given:
        def id = 1L
        def optionalWorldTime = Optional.empty()

        when:
        worldTimeService.get()

        then: "Exception will be thrown"
        def e = thrown(DataNotFoundException)
        e.message == ResponseMessage.ERROR_WORLD_TIME_NOT_FOUND

        1 * worldTimeRepository.findById(id) >> optionalWorldTime
        0 * _._
    }
}
