package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.core.blog.uitls.DateUtil;
import com.core.console.dao.userdao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by zww on 2018-12-14.
 */
@Service
public class WeatherServiceImpl implements GreatApiService {
    /**
     * 功能描述: 天气信息获取
     *
     * @param: [Url]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/12/14/00014 21:54
     */

    public String getApiContent(String Url, String cityCode) {
        Url = "http://t.weather.sojson.com/api/weather/city/" + cityCode;
        RestTemplate restTemplate = new RestTemplate();
        Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
        String nowDay = DateUtil.getStringDateShort();
        String countDown = DateUtil.getTwoDay("2019-02-19", nowDay);//计算春节剩余时间，2019年2月10日21:09:59更新为计算元宵节。
        int cede = (int) resultMap.get("status");
        if (cede == 200) {
            String cityStr = JSONUtils.toJSONString(resultMap.get("cityInfo"));
            Map cityMap = JSON.parseObject(cityStr, Map.class);
            String cityName = cityMap.get("city").toString();
            String jsonStr = JSONUtils.toJSONString(resultMap.get("data"));
            Map dataMap = JSON.parseObject(jsonStr, Map.class);
            String jokeContent = dataMap.get("forecast").toString();
            String wendu = dataMap.get("wendu").toString();
            String pm25 = dataMap.get("pm25").toString();
            String pm10 = dataMap.get("pm10").toString();
            String quality = dataMap.get("quality").toString();
            String shidu = dataMap.get("shidu").toString();
            String ganmao = dataMap.get("ganmao").toString();
            List forecasts = JSON.parseObject(jokeContent, List.class);
            for (Object forecast : forecasts) {
                Map forecastMap = JSON.parseObject(forecast.toString(), Map.class);
                if (nowDay.equals(forecastMap.get("ymd").toString())) {
                    String sunrise = forecastMap.get("sunrise").toString();
                    String high = forecastMap.get("high").toString();
                    String low = forecastMap.get("low").toString();
                    String sunset = forecastMap.get("sunset").toString();
                    String aqi = forecastMap.get("aqi").toString();
                    String ymd = forecastMap.get("ymd").toString();
                    String week = forecastMap.get("week").toString();
                    String fx = forecastMap.get("fx").toString();
                    String fl = forecastMap.get("fl").toString();
                    String type = forecastMap.get("type").toString();
                    String notice = forecastMap.get("notice").toString();
                    String weather = "贴心天气------<br/>" + cityName + "天气" + "("
                            + "日期:" + ymd + ") " + week + "<br/>" + type + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "距离元宵佳节还有" + countDown + "天" + "<br/>"
                            + "天气类型:" + type + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "当前温度:" + wendu + "℃<br/>"
                            + "空气质量:" + quality + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "湿度:" + shidu + "<br/>"
                            + "PM2.5:" + pm25 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "PM10:" + pm10 + "<br/>"
                            + "最" + high + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "最" + low + " <br/>"
                            + "风向:" + fx + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "风力:" + fl + "<br/>"
                            + "日出时间:" + sunrise + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "日落时间:" + sunset + "<br/>"
                            + "感冒建议:" + ganmao + "<br/>"
                            + "天气建议:" + notice + "<br/>";
                    return weather;
                }

            }
            return "";
        }
        return "";
    }

    @Override
    public String getApiContent(String ApiUrl) throws Exception {
        return null;
    }
}
