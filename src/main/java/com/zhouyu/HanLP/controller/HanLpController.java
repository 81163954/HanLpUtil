package com.zhouyu.HanLP.controller;

import com.zhouyu.HanLP.utils.CosineSimilarity;
import com.zhouyu.HanLP.vo.SplitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hanlp")
public class HanLpController {

    @PostMapping("split")
    public List<Double> splitText(@RequestBody SplitVO splitVO){
        String text1 = splitVO.getText();
        List<String> textList = splitVO.getTextList();

        List<Double> cosList = new ArrayList<Double>();
        for (String text2 : textList) {
            double v = CosineSimilarity.cosineSimilarity(text1, text2);
            cosList.add(v);
        }


        return cosList;
    }

}
