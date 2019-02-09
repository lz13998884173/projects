/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
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
//set the request head
  res.setHeader("Access-Control-Allow-Origin","*");
  if(req.method == "POST"){
      var result = "";
// get the router path from client-side
    var pathName = url.parse(req.url).pathname;
    req.addListener("data",function (chunk) {
      result += chunk;
    });
    
    req.on("end" , function () {
         var user = qs.parse(result);
         var sql='select * from details';
          connection.query(sql,function(err,data){
              if (!err){
            console.log("loading file successful");
            {
                
              console.log("have data in file");
//把数据转成JSON对象，以便我们使用
              var arr = JSON.parse(JSON.stringify(data));
              var obj = {};
              
                obj.City = user.City;
                obj.Street = user.Street;
                obj.Destination=user.Destination;
                arr.push(obj);
                console.log(arr);
                console.log(obj);
               var addsql='insert into details(customer_name,current_city,current_location,destination,statu)'+ 
'select username ,?,?,?,? from login_information where login="true" ';
               var addparams=[obj.City,obj.Street,obj.Destination,"undone"];
                connection.query(addsql,addparams,function(err,result){
                	if(err){
                	console.log('[insert error]-',err.message);
                	return;
                	}
                	
                	
                })
                res.end("insert successful");
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
}).listen(3011 , function (err) {
  if (!err){
    console.log("the servers start successful and listening port3011...");
  }
});

module.exports={
connection:connection,
createServer:createServer


}



