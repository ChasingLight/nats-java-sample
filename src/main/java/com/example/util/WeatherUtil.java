package com.example.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jinhaodong
 * @date 2024/5/30 17:58
 */
@Component
public class WeatherUtil {

    // 存储天气信息的列表
    private static final List<String> weatherInfoList = new ArrayList<>();

    static {
        // 添加一些天气信息到列表中
        weatherInfoList.add("Sunny");
        weatherInfoList.add("Cloudy");
        weatherInfoList.add("Rainy");
        weatherInfoList.add("Snowy");
        // 添加更多的天气信息...
    }


    public String getWeatherInfoByCityName(String cityName){
        // 对接天气api进行获取---待实现
        // 生成一个随机数来选择天气信息列表中的一个天气信息
        Random random = new Random();
        int index = random.nextInt(weatherInfoList.size());
        return weatherInfoList.get(index);
    }
}
