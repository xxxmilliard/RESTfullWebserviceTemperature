package temperature;

import org.springframework.web.client.RestTemplate;

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

public class Temperature {

    private final float temp;
    private final float temp_min;

    public Temperature(String city, String country) {
    	
		RestTemplate restTemplate = new RestTemplate();
		Weather weather = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + country + 
				"&units=metric&appid=a958cad0c0fd192c9b9741a86e8d1c4a", Weather.class);
		
		temp = weather.getList()[0].getMain().getTemp();
		
		float minimalTemperature = 100f;
		for (int i = 0; i < 25; i++) {
			if (weather.getList()[i].getMain().getTemp_min() < minimalTemperature)
				minimalTemperature = weather.getList()[i].getMain().getTemp_min();
		}
		temp_min = minimalTemperature;
    	        
    }

    public float getTemp() {
        return temp;
    }

    public float getTemp_min() {
        return temp_min;
    }
}