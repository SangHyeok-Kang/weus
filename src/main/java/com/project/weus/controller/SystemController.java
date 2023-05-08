/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.weus.controller;

import com.project.weus.model.AddUserManager;
import com.project.weus.model.SearchAddress;
import com.project.weus.model.loginModel;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author host
 */
@Controller
@Slf4j
@PropertySource("classpath:/config.properties")
public class SystemController {

    public boolean result = false;
    private String userid = "";

    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;

    @Value("${mysql.server.ip}")
    private String mysqlServerIp;
    @Value("${mysql.server.port}")
    private String mysqlServerPort;
    @Autowired
    private Environment env;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/select_signup")
    public String selectSignup() {
        return "select_signup";
    }

    @PostMapping(value = "/login.do")
    public String loginDo(@RequestParam String userid, @RequestParam String password, Model model) {
        String urls = "";
        String userName = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");
        String jdbcDriver = env.getProperty("spring.datasource.driver-class-name");

        loginModel lm_model = new loginModel(mysqlServerIp, mysqlServerPort, userName, pass, jdbcDriver);
        result = lm_model.loginResult(userid, password);
        if (result == true) {
            session.setAttribute("host", userid);

            urls = "/index";
        } else {
            model.addAttribute("msg", "로그인에 실패하였습니다 입력 정보확인 후 다시 시도해주세요.");
            model.addAttribute("url", "/login");
            urls = "/login_fail";
        }
        return urls;
    }

    @PostMapping(value = "/logout.do")
    public String logoutDo(Model model) {
        session.invalidate();
        model.addAttribute("msg", "로그아웃 되었습니다.");
        model.addAttribute("url", "/");
        return "login_fail";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "/signup";
    }

    @GetMapping("/normal_signup")
    public String normalsignupPage() {
        return "/normal_signup";
    }

    @PostMapping("/nomal_insert.do")
    public String insertNormalUser(@RequestParam String password, @RequestParam String name,
            @RequestParam String phone1, @RequestParam String phone2, @RequestParam String phone3, @RequestParam String birthdate,
            @RequestParam String major, @RequestParam String city, @RequestParam String district, @RequestParam String street, @RequestParam String detail,
            @RequestParam String gender, Model model) {
        String userName = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");
        String jdbcDriver = env.getProperty("spring.datasource.driver-class-name");
        String phone = phone1 + "-" + phone2 + "-" + phone3;

        AddUserManager manager = new AddUserManager(mysqlServerIp, mysqlServerPort, userName, pass, jdbcDriver);
        result = manager.checkId(userid);
        if (result == true) {
            manager.addRow(userid, password, name, phone, birthdate, major, city, district, street, detail, gender);

            model.addAttribute("msg", "회원가입 완료되었습니다.");
            model.addAttribute("url", "/");
        } else {
            model.addAttribute("msg", "회원가입에 실패하였습니다 입력 정보확인 후 다시 시도해주세요.");
            model.addAttribute("url", "/signup");
        }
        return "/signup_result";
    }

    @PostMapping("/insert.do")
    public String insertUserInfo(@RequestParam String password, @RequestParam String name,
            @RequestParam String phone1, @RequestParam String phone2, @RequestParam String phone3, @RequestParam String birthdate, @RequestParam String school,
            @RequestParam String major, @RequestParam String grade, @RequestParam String status, @RequestParam String interest,
            @RequestParam String postcode, @RequestParam String detail, @RequestParam String extra, @RequestParam String address, @RequestParam String gender, Model model) {
        String schoolinfo = "";
        System.out.println(postcode);
        String userName = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");
        String jdbcDriver = env.getProperty("spring.datasource.driver-class-name");
        String phone = phone1 + "-" + phone2 + "-" + phone3;

        if (major.isEmpty() && status.isEmpty()) {
            schoolinfo = school + " " + grade;
        } else {
            schoolinfo = school + " " + major + " " + grade + " " + status;
        }

        AddUserManager manager = new AddUserManager(mysqlServerIp, mysqlServerPort, userName, pass, jdbcDriver);
        result = manager.checkId(userid);
        if (result == true) {
            manager.addRow(userid, password, name, phone, birthdate, schoolinfo, interest, postcode, detail, extra, address, gender);

            model.addAttribute("msg", "회원가입 완료되었습니다.");
            model.addAttribute("url", "/");
        } else {
            model.addAttribute("msg", "회원가입에 실패하였습니다 입력 정보확인 후 다시 시도해주세요.");
            model.addAttribute("url", "/signup");
        }
        return "/signup_result";
    }

    @RequestMapping("/checkId.do")
    public String checkUserId(@RequestParam String userid, Model model) {
        this.userid = userid;
        String userName = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");
        String jdbcDriver = env.getProperty("spring.datasource.driver-class-name");
        AddUserManager manager = new AddUserManager(mysqlServerIp, mysqlServerPort, userName, pass, jdbcDriver);

        result = manager.checkId(userid);
        if (result == true) {
            model.addAttribute("msg", "사용가능한 아이디 입니다.");
        } else {
            model.addAttribute("msg", "중복된 아이디 입니다.");
        }
        return "/checkId_result";
    }

    @RequestMapping("/showMap.do")
    public String showMap(Model model) {
        String host = (String) request.getSession().getAttribute("host");
        System.out.println(host);
        String userName = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");
        String jdbcDriver = env.getProperty("spring.datasource.driver-class-name");

        SearchAddress manager = new SearchAddress(mysqlServerIp, mysqlServerPort, userName, pass, jdbcDriver);
        String juso = manager.checkAddress(host);
        model.addAttribute("juso", juso);

        return "/kakaomap";
    }
}
