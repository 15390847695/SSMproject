package com.heit.SSM.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heit.SSM.model.QUerydata;
import com.heit.SSM.model.tbadmin;
import com.heit.SSM.resp.UserInfo;
import com.heit.SSM.resp.pageResult;
import com.heit.SSM.service.IMenuServices;
import com.heit.SSM.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IMenuServices iMenuServices;
    @Autowired
    private IUserService iUserService;


    @RequestMapping("/userList")
    public String  userList(){

        return "user/userList";
    }

//    @ResponseBody
//    @RequestMapping("/getUserList")
//    public QUerydata UserInfo(){
//        List<tbadmin> list=iMenuServices.queryAll();//数据库数据
//        System.out.println("输出查询结果"+list.size());
//        QUerydata qUerydata =new QUerydata();
//        qUerydata.setCode(0);
//        qUerydata.setMsg("查询成功");
//        List<tbadmin> datalist=new ArrayList<tbadmin>();
//
//        if(CollectionUtils.isEmpty(list)){
//            return qUerydata;
//        }
//        for(tbadmin u:list){
//            tbadmin queryResp=new tbadmin();
//            queryResp.setId(u.getId());
//            queryResp.seteMail(u.geteMail());
//            queryResp.setFullname(u.getFullname());
//            queryResp.setPhone(u.getPhone());
//            queryResp.setBirthday(u.getBirthday());
//            queryResp.setAddress(u.getAddress());
//            queryResp.setSex(u.getSex());
//            queryResp.setUsername(u.getUsername());
//
//
//            datalist.add(queryResp);
//        }
//
//        qUerydata.setData(datalist);
//        return qUerydata;
//
//    }
@ResponseBody
@RequestMapping("/getUserList")
public pageResult UserInfo(@RequestParam("page")String page,
                           @RequestParam("limit")String limit){
      //int   page1=Integer.parseInt(page)-1;
//   List<UserInfo> userInfoList= iUserService.queryUserInfo();
    PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(limit));
    List<UserInfo> userInfoList=iUserService.queryAllUserInfo();

    PageInfo<UserInfo> pageInfo=new <UserInfo>PageInfo(userInfoList);
    System.out.println("PageInfo"+pageInfo);
   pageResult pageResult1 = new pageResult();
   pageResult1.setCode("0");
   pageResult1.setData(pageInfo.getList());
   pageResult1.setMsg("成功");
   pageResult1.setCount(12);
    return pageResult1;

}

    @RequestMapping("/addUser")
    public String adduserList(){
        return "user/addUser";
    }
}
