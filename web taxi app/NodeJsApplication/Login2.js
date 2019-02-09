/**
 * http://usejsdoc.org/
 */
//var express=require('express');
var http = require("http");
var url = require("url");
var qs = require("querystring");
var mysql=require('mysql');
//var app = express();

var connection=mysql.createConnection({
	user:'root',
	password:'admin',
	database:'username'
});
connection.connect();
 
var createServer=http.createServer(function (req , res) {
//设置请求头
  res.setHeader("Access-Control-Allow-Origin","*");
  if(req.method == "POST"){
    //接收发来的用户名和密码
    var result = "";
//获取前端代码发来的路由地址
    var pathName = url.parse(req.url).pathname;
   // console.log(pathName);
    req.addListener("data",function (chunk) {
      result += chunk;
    });
    
 
    req.on("end" , function () {
      var user = qs.parse(result);
      //console.log(user);
      
  
   
     
      //判断用户是否存在
      if(user.username){
       if(user.user=="Customer"){
    	   var sql='select * from Login_Information';
    	   }
       else{
    	   var sql='select * from Drivers_Information';
    	   }
           
           //data包含整个user数据库的数据
    	  connection.query(sql,function(err,data){
          if (!err){
            console.log("operate database successful");
           
            {
              console.log("have data in database");
//把数据转成JSON对象，以便我们使用
              var arr = JSON.parse(JSON.stringify(data));
              //console.log(arr);
            
              
             
//遍历整个保存数据的数组 判断登录注册
              for(var i = 0;i < arr.length;i++){
                var obj = arr[i];
                
                
               //比较输入的用户名和数据库的用户名
                if(obj.username == user.username){
                    //如果是登录界面，在比较密码然后登陆成功
                  if(pathName == "/login"){
                    if (obj.password == user.password){
                        
                var obj = {};
                obj.username = user.username;
                obj.password = user.password;
                arr.push(obj);
                console.log(obj);
                if(user.user=='Customer'){
                var addsql='update Login_Information set login= "true" where username=?';
            }
            else {
                var addsql='update Drivers_Information set login= "true" where username=?';
            }
               var addparams=[obj.username];
               connection.query(addsql,addparams,function(err,result){
                	if(err){
                	console.log('[insert error]-',err.message);
                	return;
                	}
                	
                })
                        
                        var data=JSON.stringify(obj);
                      if(user.user=="Customer"){  
                      res.end("customer login successful!");
                  }
                  else{
                      res.end("driver login successful!");
                  }
                      
                      
                      return;
                    }else {
                      res.end("password fault！");
                      return;
                    }
                  }
                  //如果是注册界面，用户名已存在不能再注册
                  if(pathName == "/register"){
                    res.end("username has been exit!");
                    return;
                  }
                }
              }
              //没有找到匹配的用户名，所以用户名不存在
              if(pathName == "/login"){
                res.end("username not exit!");
                return;
              }
              //创建一个新的用户名
              if(pathName == "/register"){
//创建新对象写入数据
                var obj = {};
                obj.username = user.username;
                obj.password = user.password;
                arr.push(obj);
                console.log(obj);
                if(user.user=="Customer"){
                	var addsql='insert into Login_Information(username,password,login) values (?,?,? )';
                }else
                	var addsql='insert into Drivers_Information(username,password,login) values (?,?,?)';
                
                var addparams=[obj.username,obj.password,'false'];
                connection.query(addsql,addparams,function(err,result){
                	if(err){
                	console.log('[insert error]-',err.message);
                	return;
                	}
                	
                })
                res.end("regist successful!");
              
                return;
              }
            }
          }else  {
            console.log("operate database FAILED");
          }
        })
      }
    });
  }else  {
    res.end("connect failed");
  }
}).listen(3010 , function (err) {
  if (!err){
    console.log("the servers start successful and listening port3010...");
  }
});

module.exports={
connection:connection,
createServer:createServer


}



