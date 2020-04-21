package com.core.blog.control;


import com.core.blog.po.StudentScores;
import com.core.blog.service.StudentScoresServer;
import com.core.blog.service.StudentScoresServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.core.blog.uitls.ExcelUtil.readExcelBody;

@Controller
@RequestMapping(value = "/score")
public class QueryStudentScoresController {

    @Autowired
    private StudentScoresServer scoresServer;
    private static final Logger logger = LoggerFactory.getLogger(StudentScoresServerImpl.class);

    @RequestMapping(value = "/queryScores/{name}")
    public String queryScores(@PathVariable String name, Model model) {
        try {
            List<StudentScores> studentScores = scoresServer.findByName(name);
            model.addAttribute("scores",studentScores);
            return "scores";
        } catch (Exception e) {
            logger.error("queryScores error", e.getStackTrace());
        }
        return null;
    }

    @RequestMapping(value = "/deleteScores/{title}")
    @ResponseBody
    public String deleteScoresByTitle(@PathVariable String title) {
        int n = scoresServer.deleteByTitle(title);
        if (n > 0) {
            logger.info("删除{}这个成功", title);
            return "删除" + title + "这个成功";
        } else {
            logger.error("删除{}这个失败 速速查看", title);
            return "删除" + title + "这个失败";
        }
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public String insert() {
        try {
            List<List<Object>> list = readExcelBody("C:\\Users\\ZHANGWEI\\Desktop\\汉中市第二次联考.xlsx", 0);
            List<Object[]> dataList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Object[] objArr = new Object[list.get(i).size()];
                List<Object> objList = list.get(i);
                for (int j = 0; j < objList.size(); j++) {
                    objArr[j] = objList.get(j);
                }
                dataList.add(objArr);
            }
            if (dataList.size() > 0) {
                List<StudentScores> studentScores = this.arraryConvert(dataList, "汉中市第二次联考");//todo 记得改名字
                logger.info("读取到" + studentScores.size() + "条数据，正在写入中>>>>>>>>>>>>>>>>>>>>>>>");
                scoresServer.insert(studentScores);
                logger.info("写入完成");
            }
            for (int i = 0; i < dataList.size(); i++) {
                System.out.println(Arrays.toString(dataList.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private List<StudentScores> arraryConvert(List<Object[]> dataList, String title) {
        ArrayList<StudentScores> studentScoreList = new ArrayList();
        try {
            for (Object[] objects : dataList) {
                StudentScores studentScores = new StudentScores();
                studentScores.setClasses(objects[0].toString());
                studentScores.setName(objects[1].toString());
                studentScores.setChinese(objects[2].toString());
                studentScores.setMath(objects[3].toString());
                studentScores.setEnglish(objects[4].toString());
                studentScores.setWuli(objects[5].toString());
                studentScores.setHuaxue(objects[6].toString());
                studentScores.setShengwu(objects[7].toString());
                studentScores.setLizonghe(objects[8].toString());
                studentScores.setTotal(objects[9].toString());
                studentScores.setGradeRank(objects[10].toString());
                studentScores.setClassRank(objects[11].toString());
                studentScores.setTestTitle(title);
                studentScoreList.add(studentScores);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentScoreList;
    }
}
