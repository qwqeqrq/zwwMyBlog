package com.core.blog.service;

import com.core.blog.mapper.DouYuLiveMapper;
import com.core.socket.DouyuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/10/4/00004
 * @Description:
 */
@Service
public class DouYuLiveDtoImp implements DouYuLiveService {
    @Autowired
    DouYuLiveMapper douYuLiveDao;
    @Value("${wzurl}")
    private String wzurl;
    @Value("${lolurl}")
    private String lolurl;
    @Value("${lsurl}")
    private String lsurl;
    @Override
    public Integer saveDouYuLive() {
        int result = 0;
        try {
            URL douYuUrl = new URL(lolurl);
            System.out.println("我是斗鱼TV的主机:" + douYuUrl.getHost());
            try {
                InputStream inputStream = douYuUrl.openStream();//获取流信息(字节输入流)
                //将字节输入流转化为字符输入流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                StringBuffer stringBuffer = new StringBuffer();
                char[] c = new char[2048];
                while (inputStreamReader.read() != -1) {
                    inputStreamReader.read(c);
                    stringBuffer.append(c);
                }
                String html = stringBuffer.toString().replaceAll(" ", "");
                //获得斗鱼直播相关信息
                List<DouyuDto> douyuDtoList = getDouyuLabel(html);
                if (!douyuDtoList.isEmpty()&&douyuDtoList!=null){

                    for (DouyuDto douyuDto:douyuDtoList) {
                       result =  douYuLiveDao.insert(douyuDto);//将斗鱼信息保存到数据库中
                    }
                    result = douyuDtoList.size();
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 功能描述: 取出字符串中的汉字部分，去掉其他字符
     *
     * @param: [str]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/9/30/00030 12:30
     */

    private static String getChinese(String str) {
        String reg = "[^\u4e00-\u9fa5]";
        str = str.replaceAll(reg, "");
        return str;
    }

    /**
     * 功能描述: 将网页信息整合成bean对象
     *
     * @param: [html, indexOfString]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/9/30/00030 12:40
     */
    private static List<DouyuDto> getDouyuLabel(String html) {
        List<DouyuDto> douyuDtos = new ArrayList<>();
        String title = "<h3class=\"ellipsis\">";//直播间标题
        String anchor = "<imgalt=\"";//主播
        String anchorNum = "data-sub_rt=\"0\"href=\"/";//直播间号
        String vimNum = "<spanclass=\"dy-numfr\">";//人气
        String lab = "<divclass=\"impress-tag-list\"><br/>" +
                "<spanclass=\"impress-tag-item\"target=\"_blank\"<br/>" +
                "data-url=\"https://www.douyu";//标签
        for (int i = 0; i < html.length(); i++) {
            DouyuDto douyuDto = new DouyuDto();
            int indexEnd = html.indexOf(anchor);
            int label = html.indexOf(vimNum);
            if (indexEnd == -1) {
                break;
            }
            String tie = getStringsub(html, title, 20, 40);
            String anc = getStringsub(html, anchor, 8, 20);
            String ancn = getStringsub(html, anchorNum, 21, 29);
            String vmn = getStringsub(html, vimNum, 21, 27);
            String lb = getStringsub(html, lab,129 , 229);
            douyuDto.setTitle(getChinese(tie));
            douyuDto.setLiveNumber(getNumber(ancn));
            douyuDto.setVimNumber(finall(vmn));
            douyuDto.setAnchor(getChinese(anc).replaceAll("的直播",""));
            douyuDtos.add(douyuDto);
           /* System.out.println(douyuDto.toString());
            System.out.println("直播间标题：" + getChinese(tie));
            System.out.println("主播：" + getChinese(anc).replaceAll("的直播",""));
            System.out.println("直播间号：" + getNumber(ancn));
            System.out.println("人气：" + finall(vmn));
            System.out.println("标签：" + getChinese(lb));
            System.out.println("---------------------------------");*/
            html = html.substring(label + 500+70, html.length());
        }
        return douyuDtos;
    }

    /**
     * 功能描述: 将字符串片段按照关键字符串进行截取
     *
     * @param: [str, indexOfString, start, end]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/9/30/00030 12:46
     */
    private static String getStringsub(String str, String indexOfString, int start, int end) {
        int index = str.indexOf(indexOfString);
        str = str.substring(index + start, index + end);
        return str;
    }

    /**
     *
     * 功能描述: 获取字符串中的数字
     *
     * @param: [str]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/9/30/00030 23:55
     */
    private static String getNumber(String str){
        String regEx="[^0-9]";
        str = str.replaceAll(regEx, "");
        return str;
    }

    /**
     *
     * 功能描述: 取出《，》
     *
     * @param: [str]
     * @return: java.lang.String
     * @auther: ZHANGWEI
     * @date: 2018/10/1/00001 0:04
     */
    private static String finall(String str){
        str =str.replaceAll(">","").replaceAll("<","");
        return str;
    }
}
