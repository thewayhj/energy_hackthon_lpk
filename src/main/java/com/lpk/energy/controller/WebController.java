package com.lpk.energy.controller;

import com.lpk.energy.TimeTableLoad;
import com.lpk.energy.TimeTableMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HeeJoongKim on 2017-04-07.
 */
@Controller
public class WebController
{

    @Autowired
    TimeTableMongoRepository timeTableMongoRepository;

    @RequestMapping(value="/")
    public String main(){
        return "main";
    }
    @RequestMapping(value="/main")
    public String mainframe(){
        return "main";
    }

    @RequestMapping(value="/flot")
    public String flot(){
        return "flot";
    }

    @RequestMapping(value="/test")
    public String test(){

        TimeTableLoad timeTableLoad = new TimeTableLoad();


        timeTableMongoRepository.save(timeTableLoad.send());

        return "index";
    }

    @RequestMapping(value="/morris")
    public String morris(){
        return "morris";
    }
    @RequestMapping(value="/tables")
    public String tables(){
        return "tables";
    }

    @RequestMapping(value="/calendar")
    public String calendar(){
        return "calendar";
    }
}
