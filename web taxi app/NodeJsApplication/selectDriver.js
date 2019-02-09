/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var express = require('express');
var http = require("http");
var url = require("url");
var qs = require("querystring");
var app = express();

var mysql = require('mysql');
var connection = mysql.createConnection({
    user : 'root',
    password : 'admin',
    database : 'username'
})
connection.connect();
var createServer=http.createServer(function (req , res) {
//设置请求头
  res.setHeader("Access-Control-Allow-Origin","*");
  if(req.method == "POST"){
      var result = "";
//获取前端代码发来的路由地址
    var pathName = url.parse(req.url).pathname;
    req.addListener("data",function (chunk) {
      result += chunk;
    });
    
    req.on("end" , function () {
         var user = qs.parse(result);
         var sql='select * from details;';
        
          connection.query(sql,function(err,data){
              if (!err){
            console.log("loading file successful");
            {
                
              console.log("have data in file");
//把数据转成JSON对象，以便我们使用
              var arr = JSON.parse(JSON.stringify(data));
              var obj = {};
              
                obj.name = user.name;
                
                
                arr.push(obj);
                console.log(obj);
               var addsql='update details set statu="done" where Driver_name=? and statu="undone" and Customer_name=(select Customer_name from login_information where login="true");';
                var addsql1='select * from details where Driver_name=? and statu="undone";';
                      var addparams=[obj.name];
                      connection.query(addsql1,addparams,function(err,result){
                          if(err){
                              console.log('[insert error]-',err.message);
                              return
                          }console.log("result:"+result);
                          if(result==""){
                              res.end("selected error")
                          } else 
                              res.end("you have selected "+obj.name+" as your driver, service started");
                      })
                connection.query(addsql,addparams,function(err,result){
                	if(err){
                	console.log('[insert error]-',err.message);
  
                	return;
                	}
                	
                	
                })
              //  res.end("you have selected "+obj.name+" as your driver, service started");
                return;
            }
           
            
            
              }else{
                  console.log('loading file failed');
                  
              }
              
          })
    })
    
  }else  {
    res.end("connect failed");
  }
}).listen(3018 , function (err) {
  if (!err){
    console.log("the servers start successful and listening port3018...");
  }
});

