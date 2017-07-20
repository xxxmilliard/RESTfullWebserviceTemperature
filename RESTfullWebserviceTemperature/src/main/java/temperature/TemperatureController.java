package temperature;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladimir Kiva
 * 
 * Используя Spring Boot, сделать веб-приложение, которое принимает 
 * REST-подобный HTTP запрос, где передаётся название города (например "Moscow") 
 * и код страны (например "ru"), и возвращает текущую температуру в этом городе 
 * и минимальную из прогнозируемых температур в ближайшие 3 дня в JSON формате.
 * Для сборки приложения использовать Maven или Gradle.
 * Приложение должно быть самодостаточным, запускаться из командной строки и не 
 * требовать отдельно установленных servlet контейнеров, application серверов и т.п.
 * 
 * Запрос имеет такой формат: http://localhost:8080/temperature?city=Moscow&country=ru
 * Формат ответа: {"temp":17.19,"temp_min":12.58}
 */

@RestController
public class TemperatureController {

    @RequestMapping("/temperature")
    public Temperature temperature(@RequestParam(value="city", required=true) String city, 
    		@RequestParam(value="country", required=true) String country) {
        return new Temperature(city, country);
    }
}