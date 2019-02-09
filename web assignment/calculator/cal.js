// JavaScript Document

	
var $ = function(id) { return document.getElementById(id); };
var num1;
var num2;

var result='';//save the result of calculator
var operator;//get the button for + - * /
var flag=false;
var Input=function(id){      
	var txt=$("tb").innerHTML;  
	if(txt=='0'){
		if(id=="b."){
			$("tb").innerHTML="0";}
			else
		$("tb").innerHTML="";
	}
	var num=$(id).innerHTML; 
	if($("tb").innerHTML.length<10){
	$("tb").innerHTML+=num; //num to get the value of the button and show in the screen
	}
	
}
var optype=function(i){    
	operator=i;

	
		num1=parseFloat($("tb").innerHTML);//get the  the value in the screen and change the string to int,save it in num1
		
		flag=true;// it is for the judge in the calculation
		
			
		$('tb').firstChild.nodeValue="";
}
var calculate=function(){ 
	
	if(result==''){      //the first time click the button "equal"
		flag=false;
		num2=parseFloat($('tb').innerHTML); //save the second number inputed in the screen to num2
		if(operator=='b+'){
			result=num1+num2;
			$('tb').innerHTML=result;
			}	
		else if(operator=='b-'){
			result=num1-num2;
			$('tb').innerHTML=result;
			}	
		else if(operator=='b*'){
			result=num1*num2;
			$('tb').innerHTML=result;}	
		else if(operator=='b/'){
			if(num2==0){
				alert("divider can not be 0")}
			else
			result=num1/num2;
			$('tb').innerHTML=result.toFixed(5);}	
			
			}
	else if(flag==true){      //after the first time to click the equal,if you click the operational character
		num2=parseFloat($('tb').innerHTML);
		flag=false;
		if(operator=='b+'){
			result=result+num2;
			$('tb').innerHTML=result;
			}	
		else if(operator=='b-'){
			result=result-num2;
			$('tb').innerHTML=result;
			}	
		else if(operator=='b*'){
			result=result*num2;
			$('tb').innerHTML=result;
			}	
		else if(operator=='b/'){
			if(num2==0){
				alert("divider can not be 0")}
			else
			result=result/num2;
			$('tb').innerHTML=result.toFixed(5);
			
			}	
		}
	else if(flag==false){      
	//after the first time to click the equal,if you continue to click equal,it can calculate the value by the last clicked operational character
		result=parseFloat($('tb').innerHTML);
			if(operator=='b+'){
				result=result+num2;
				$('tb').innerHTML=result}	
			else if(operator=='b-'){
				result=result-num2;
				$('tb').innerHTML=result;}	
			else if(operator=='b*'){
				result=result*num2;
				$('tb').innerHTML=result;}	
			else if(operator=='b/'){
				if(num2==0){
				alert("divider can not be 0")}
			else
				result=result/num2;
			$('tb').innerHTML=result.toFixed(5);}	
			}
			
}


	var  clean=function(){
		num1='';
		num2='';
		result='';
		$('tb').firstChild.nodeValue='0';
		}
	
	