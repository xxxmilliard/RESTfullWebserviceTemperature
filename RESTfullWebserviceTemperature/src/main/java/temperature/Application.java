package temperature;

import org.springframework.web.client.RestTemplate;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

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

@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		Weather weather = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/forecast?q=Moscow,ru&units=metric&appid=a958cad0c0fd192c9b9741a86e8d1c4a", 
				Weather.class);
		
		System.out.println("Сейчас температура в Москве: " + weather.getList()[0].getMain().getTemp() + "C");
		
		float minTemp = 100f;
		for (int i = 0; i < 25; i++) {
			if (weather.getList()[i].getMain().getTemp_min() < minTemp)
				minTemp = weather.getList()[i].getMain().getTemp_min();
		}
		System.out.println("Минимальная температура в Москве в течение 3 суток: " + minTemp + "C");
		
		SpringApplication.run(Application.class, args);
	}
}