/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var http = require("http");
var url = require("url");
var qs = require("querystring");
var mysql=require('mysql');

var connection=mysql.createConnection({
	user:'root',
	password:'admin',
	database:'username'
});
connection.connect();

var createServer=http.createServer(function (req , res) {
    res.setHeader("Access-Control-Allow-Origin","*");
  if(req.method == "POST"){
    var result = "";
//获取前端代码发来的路由地址
    var pathName = url.parse(req.url).pathname;
   // console.log(pathName);
    req.addListener("data",function (chunk) {
      result += chunk;
    });
    req.on("end" , function () {
      var user = qs.parse(result);
      console.log(pathName);
      
      if(pathName =='/location'){                            
                   
                   var sql1="update Login_Information set login='false'";
                 
                   connection.query(sql1,function(err,data){
                       if(err){
                	console.log('[insert error]-',err.message);
                	return;
                	}
                       console.log("log out successed")
                   });
                   
               
           }
           if(pathName =='/selectOrder'){                            
                   
                   var sql1="update Drivers_Information set login='false'";
                 
                   connection.query(sql1,function(err,data){
                       if(err){
                	console.log('[insert error]-',err.message);
                	return;
                	}
                       console.log("log out successed")
                   });
                   
               
           }
      
    })
       }else  {
    res.end("connect failed");
  }
}).listen(3012 , function (err) {
  if (!err){
    console.log("the servers start successful and listening port3012...");
  }
});
