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

sql = 'select * from message where Driver_name=(select username from Drivers_Information where login="true");';

var arr = [];
var createServer=http.createServer(function (req , res) {
     res.setHeader("Access-Control-Allow-Origin","*");
     if(req.method == "POST"){
         console.log("connected");
connection.query(sql,function (err, results) {
    if (err){
        console.log(err)
    }else{
        console.log(results);
        for(var i = 0;i < results.length;i++){
            arr[i] = results[i];
            console.log(i+"条"+arr[i].Customer_name);
           // res.end(arr[i]);
            console.log("arr:"+arr[i].current_city);
        }
        var data=JSON.stringify(arr);
             res.end(data);
        console.log("data equals"+data);
        
            console.log("send")
        //console.log(arr);

        //app.post('../HTML5Application2/public_html/main page/order.html',function (req, res) {
            //res.send("demo",{"name":"test"});  //这里必须用res.send,因为有数据返回到客户端
             // res.end("success");
            //res.json({params:params});
       // })
       
    }
})

            
       
     }else {
          res.end("connect failed");
     }
}).listen(3017 , function (err) {
    if (!err){
    console.log("the servers start successful and listening port3017...");
}
})

//app.listen(3001);


