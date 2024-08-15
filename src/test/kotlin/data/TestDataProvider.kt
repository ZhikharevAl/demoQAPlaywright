package data

import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

class TestDataProvider {
    companion object {
        @JvmStatic
        fun stateAndCityProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.of("NCR", "Delhi"),
                Arguments.of("Uttar Pradesh", "Agra"),
                Arguments.of("Haryana", "Karnal"),
            )
    }
}
