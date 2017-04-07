package com.lpk.energy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HeeJoongKim on 2017-04-07.
 */
@Controller
public class WebController
{
    @RequestMapping(value="/")
    public String main(){
        return "index";
    }

    @RequestMapping(value="/widgettest")
    public String widgettest(){
        return "widgettest";
    }
}
