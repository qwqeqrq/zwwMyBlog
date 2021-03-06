package com.core.blog.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.core.blog.uitls.DateUtils;
import com.core.console.service.EmailScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        //Url = "http://t.weather.itboy.net/api/weather/city/" + cityCode;；这个域名被停用了
        // Url = "http://t.weather.sojson.com/api/weather/city/" + cityCode; 2020年7月31日  这个域名也不提供服务了 重新找一个
        Url = "https://tianqiapi.com/free/day?appid=23035354&appsecret=8YvlPNrz&city=" + cityCode;
        RestTemplate restTemplate = new RestTemplate();
        String date = "2050-01-01";
        String title = "赶紧去日程表添加吧";
        try {
            Map resultMap = restTemplate.getForEntity(Url, Map.class).getBody();
            String nowDay = DateUtils.getStringDateShort();
            Map map = emailScheduleService.getEmailSchedule(userId);
            if (map != null && map.size() > 0) {
                date = map.get("date").toString();
                title = map.get("title").toString();
            }
            String countDown = DateUtils.getTwoDay(date, nowDay);//计算时间差
            if ("0".equals(countDown)) {
                int id = (int) map.get("id");
                emailScheduleService.updateEmailSchedule(id);
            }

            String cityName = Optional.ofNullable(resultMap.get("city")).orElse("").toString();
            String wendu = Optional.ofNullable(resultMap.get("tem")).orElse("").toString();
            String quality = Optional.ofNullable(resultMap.get("air")).orElse("").toString();
            String shidu = Optional.ofNullable(resultMap.get("shidu")).orElse("").toString();
            String ganmao = Optional.ofNullable(resultMap.get("ganmao")).orElse("").toString();
            String sunrise = Optional.ofNullable(resultMap.get("sunrise")).orElse("").toString();
            String high = Optional.ofNullable(resultMap.get("tem_day")).orElse("").toString();
            String low = Optional.ofNullable(resultMap.get("tem_night")).orElse("").toString();
            String sunset = Optional.ofNullable(resultMap.get("sunset")).orElse("").toString();
            String ymd = Optional.ofNullable(resultMap.get("ymd")).orElse("").toString();
            String week = Optional.ofNullable(resultMap.get("week")).orElse("").toString();
            String fx = Optional.ofNullable(resultMap.get("win")).orElse("").toString();
            String fl = Optional.ofNullable(resultMap.get("win_meter")).orElse("").toString();
            String type = Optional.ofNullable(resultMap.get("wea")).orElse("").toString();
            String notice = Optional.ofNullable(resultMap.get("notice")).orElse("").toString();
            String weather = "贴心天气------<br/>" + cityName + "天气" + "("
                    + "日期:" + ymd + ") " + week + "<br/>" + type + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + title + countDown + "天" + "<br/>"
                    + "天气类型:" + type + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "当前温度:" + wendu + "℃<br/>"
                    + "空气质量:" + quality + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "湿度:" + shidu + "<br/>"
                    + "最" + high + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "最" + low + " <br/>"
                    + "风向:" + fx + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "风力:" + fl + "<br/>"
                    + "日出时间:" + sunrise + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "日落时间:" + sunset + "<br/>"
                    + "感冒建议:" + ganmao + "<br/>"
                    + "天气建议:" + notice + "<br/>";
            return weather;

        } catch (
                Exception e) {
            logger.error("天气获取失败>>>>>>>>>>>>>>>>>{}", e);
        }
        return "";
    }

    @Override
    public String getApiContent(String ApiUrl) throws Exception {
        return null;
    }

}
