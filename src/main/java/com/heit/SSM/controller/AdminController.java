package com.heit.SSM.controller;
import com.google.code.kaptcha.Producer;

import com.heit.SSM.req.LoginReq;
import com.heit.SSM.resp.MenuResp;
import com.heit.SSM.resp.RespVo;
import com.heit.SSM.service.IMenuServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/sys")
public class AdminController {

    private static final Logger logger= LoggerFactory.getLogger(AdminController.class);

    @Autowired

    private Producer captchaProducer=null;

    @Autowired
    private IMenuServices iMenuServices;

    @ResponseBody     //不加会报 Required String parameter 'userName' is not present
    @RequestMapping("/login")
    public RespVo login(@RequestParam("username")String userName,
                        @RequestParam("password")String password,
                        @RequestParam("vcode") String vcode,
                        HttpServletRequest request) {
        logger.info("用户登录请求参数", vcode);
        if (StringUtils.isEmpty(vcode)) {
            logger.info("验证码为空");
            return RespVo.getFail("验证码为空");
        }
        if (StringUtils.isEmpty(password)){
            logger.info("password为空");
            return RespVo.getFail("password为空");
        }
        if (StringUtils.isEmpty(userName)){
            logger.info("userName为空");
            return RespVo.getFail("userName为空");
        }

        String sessionCode = (String)request.getSession().getAttribute("kaptcha");

        if(!vcode.equals(sessionCode)){
            return RespVo.getFail("输入验证码不正确");
        }
        /**
         * 生成验证码
         */
//shiro 登录逻辑 TODO

        Subject subject = SecurityUtils.getSubject();

        //明文转成MD5
        String md5Password = new Md5Hash(password).toString();




        logger.info("加密后的密码："+ md5Password);



        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,md5Password);

       // 判断是否登录
        if(!subject.isAuthenticated()){
            try{
                subject.login(usernamePasswordToken);
            }catch (AuthenticationException e){ //TODO
                logger.error("AuthenticationException",e);
                return RespVo.getFail("登录失败");
            }

        }

        return RespVo.getSuccess();

    }

    @ResponseBody     //不加会报 Required String parameter 'userName' is not present
    @RequestMapping("/loginOut")
    public RespVo loginOut(HttpServletRequest request) {
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return null;
    }

    @RequestMapping("/vcode")
    public void createKaptcha(HttpServletRequest request, HttpServletResponse response){
        logger.info("生成验证码");
        String text= captchaProducer.createText();
        logger.info("验证码：",text);
        BufferedImage image=captchaProducer.createImage(text);
        request.getSession().setAttribute("kaptcha",text);
        try {
            ImageIO.write(image,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("IOException",e);
        }
    }

    @ResponseBody
    @RequestMapping("/getMenus")
    public MenuResp loadMenus(){
        MenuResp menuResp=iMenuServices.getMenus();
        logger.info("加载左侧菜单");
        return menuResp;
    }
    @RequestMapping("/main")
    public String loadMain(){
        return "/main.jsp";
    }

    @RequestMapping("/index")
    public String index(){
        return "redirect:/index.jsp";
    }


}
