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
                obj.price = user.price;
                
                arr.push(obj);
                console.log(obj);
               var addsql='update details set Driver_name=(select username from Drivers_Information where login="true"), Price=? where Customer_name=? and statu="undone";';
               var addsql1='select Customer_name from details where  statu="undone"; ';
                      var addparams=[obj.price,obj.name];
                      var addparams1=[obj.name];
                       connection.query(addsql1,addparams1,function(err,result){
                           if(err){
                              console.log('[insert error]-',err.message);
                              return
                          }
                          var judge=false;
                          for(i=0;i<result.length;i++){
                              if(result[i].Customer_name==obj.name)
                                  judge=true;
                          }
                         
                          if(judge==false)
                              res.end("No such Order");
                          else
                       connection.query(addsql,addparams,function(err,result){
                	if(err){
                	console.log('[insert error]-',err.message);
                	return;
                	}
                	 res.end("get order successful");
                	
                })
                              
                       })
                
               
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
}).listen(3014 , function (err) {
  if (!err){
    console.log("the servers start successful and listening port3014...");
  }
});

