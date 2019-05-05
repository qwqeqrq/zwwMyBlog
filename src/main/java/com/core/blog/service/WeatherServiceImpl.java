package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.core.blog.uitls.DateUtil;
import com.core.console.service.EmailScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private EmailScheduleService emailScheduleService;

    /**
     * 功能描述: 天气信息获取
     *
     * @param: [Url]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/12/14/00014 21:54 计算春节剩余时间，2019年2月10日21:09:59更新为计算元宵节。 2019年3月30日01:40:22 更新为计算五一还有多久
     * 2019年5月5日11:17:07 新增日程表 维护日期计算
     */
    public String getApiContent(String Url, String cityCode, int userId) {
        Url = "http://t.weather.sojson.com/api/weather/city/" + cityCode;
        RestTemplate restTemplate = new RestTemplate();
        String date = "2050-01-01";
        String title = "赶紧去日程表添加吧";
        try {
            Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
            String nowDay = DateUtil.getStringDateShort();
            Map map = emailScheduleService.getEmailSchedule(userId);
            if (map != null && map.size() > 0) {
                date = map.get("date").toString();
                title = map.get("title").toString();
            }
            String countDown = DateUtil.getTwoDay(date, nowDay);//计算时间差
            if ("0".equals(countDown)) {
                int id = (int) map.get("id");
                emailScheduleService.updateEmailSchedule(id);
            }
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
                                + "日期:" + ymd + ") " + week + "<br/>" + type + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + title + countDown + "天" + "<br/>"
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
        } catch (Exception e) {
            logger.error("天气获取失败>>>>>>>>>>>>>>>>>" + e.getMessage());
        }
        return "";
    }

    @Override
    public String getApiContent(String ApiUrl) throws Exception {
        return null;
    }

}
