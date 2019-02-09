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

sql = 'select Customer_name,current_city,Current_location,Destination from details where statu="undone"';

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
     
            console.log("arr:"+arr[i].current_city);
        }
        var data=JSON.stringify(arr);
             res.end(data);
        console.log("data equals"+data);
        
            console.log("send")
       
       
    }
})

            
       
     }else {
          res.end("connect failed");
     }
}).listen(3016 , function (err) {
    if (!err){
    console.log("the servers start successful and listening port3016...");
}
})

//app.listen(3001);


