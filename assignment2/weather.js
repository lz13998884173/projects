var $=function(id){
	return document.getElementById(id);
}

var search=function(url){
	var request=getHttpObejct();
	if(request){
		request.onreadystatechange=function(){
			parseResponse(request);
		};
		request.open("GET",url,true);
		request.send(null);
		$("a1").style.display='block';
	}
}
var getHttpObejct=function(){
	var xhr=false;
	if(window.XMLHttpRequest){
		xhr=new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		try{
			xhr=new ActiveXObject("Msxm12.XMLHTTP");
		}catch(e){
			try{
				xhr=new ActiveObject("Microsoft.XMLHTTP");
			}catch(e){
				xhr=false;
			}
		}
	}
	return xhr;
}
var parseResponse=function(request){
	if(request.readyState==4){
		if(request.status==200||request.status==304){
			var fivedays = JSON.parse(request.responseText);
			var list = fivedays.list;
			var temp_c=[];
			var hours=[];
			var day;
			for(var i=0;i<8;i++){
				temp_c.push((list[i].main.temp-273.15).toFixed(3));
				hours.push((fivedays.list[i].dt_txt).substr(11,5));
				day=(fivedays.list[i].dt_txt).substr(0,10);
			}
			console.log(hours);
			var ctx = document.getElementById("canvas").getContext("2d");
		    window.myLine = new Chart(ctx,setconfig(hours,temp_c,day));
		}
		else if(request.status==404)
			alert("Sorry, we can't find this city in this area!");
		else
			alert("Sorry, we have some problem in server.");
	}
}
var setconfig=function(hours,temp_c,day){
	var config = {
            type: 'line',
            data: {
                labels: hours,
                datasets: [{
                	label: 'Celsius',
                    backgroundColor: 'rgb(0, 160, 160)',
                    borderColor: 'rgb(0, 150, 0)',
                    data: temp_c,
                    fill: false
                }]
            },
             options: {
                scales: {
                    xAxes: [{display: true,scaleLabel: {display: true,labelString: day}}],
                    yAxes: [{display: true,scaleLabel: {display: true,labelString: 'temp'}}]
                }
            }
        };
	return config;
}

var shift=function(url){
    var request;
    if(window.XMLHttpRequest){
        request=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        try{
            request=new ActiveXObject("Msxm12.XMLHTTP");
        }catch(e){
            try{
                request=new ActiveObject("Microsoft.XMLHTTP");
            }catch(e){
                request=false;
            }
        }
    }
    if(request){
        request.onreadystatechange=function(){
            parseResponse_1(request);
        };
        request.open("GET",url,true);
        request.send(null);
    }
}
var parseResponse_1=function(request){
    if(request.readyState==4){
        if(request.status==200||request.status==304){
            console.log(request.responseXML);
            var weather=request.responseXML;
            var time=weather.getElementsByTagName("time")[0];
            var location=weather.getElementsByTagName("country")[0].innerHTML;
            var weather_name=time.getElementsByTagName("symbol")[0].getAttribute("name");
            var weather_windSpeed=time.getElementsByTagName('windSpeed')[0].getAttribute('mps');
            var weather_pressure_value=time.getElementsByTagName('pressure')[0].getAttribute('value');
            var weather_humidity =time.getElementsByTagName('humidity')[0].getAttribute('value');
            var weather_clouds =time.getElementsByTagName('clouds')[0].getAttribute('value');

            $("t1").innerHTML=weather_name;
            $("t2").innerHTML=weather_windSpeed;
            $("t3").innerHTML=weather_pressure_value+"hPa";
            $("t4").innerHTML=weather_humidity+"%";
            $("t5").innerHTML=weather_clouds;
            $("area").innerHTML=location;
        }
        else if(request.status==404)
            alert("Sorry, we can't find this city in this area!");
        else
            alert("Sorry, we have some problem in server.");
    }
}

var forest=function(url){
    var request;
    if(window.XMLHttpRequest){
        request=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        try{
            request=new ActiveXObject("Msxm12.XMLHTTP");
        }catch(e){
            try{
                request=new ActiveObject("Microsoft.XMLHTTP");
            }catch(e){
                request=false;
            }
        }
    }
    if(request){
        request.onreadystatechange=function(){
            parseResponse_2(request);
        };
        request.open("GET",url,true);
        request.send(null);
    }
}


var parseResponse_2=function(request){
    if(request.readyState==4){
        if(request.status==200||request.status==304){
            console.log(request.responseXML);
            var weather=request.responseXML;


            var weather_id=[];
            for(var i=0;i<8;i++){

                var
                time=weather.getElementsByTagName("time")[i].getAttribute('from');

                weather_id[i]=weather.getElementsByTagName("time")[i].getElementsByTagName("symbol")[0].getAttribute('var');
                if(i==0){
                	$('d1').innerHTML=weather.getElementsByTagName("time")[0].getAttribute('from').substr(11,8);
                	$('d9').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
				}
				else if(i==1){
                    $('d2').innerHTML=weather.getElementsByTagName("time")[1].getAttribute('from').substr(11,8);
                    $('d10').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
				}
                else if(i==2){
                    $('d3').innerHTML=weather.getElementsByTagName("time")[2].getAttribute('from').substr(11,8);
                    $('d11').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
                }
                else if(i==3){
                    $('d4').innerHTML=weather.getElementsByTagName("time")[3].getAttribute('from').substr(11,8);
                    $('d12').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
                }
                else if(i==4){
                    $('d5').innerHTML=weather.getElementsByTagName("time")[4].getAttribute('from').substr(11,8);
                    $('d13').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
                }
                else if(i==5){
                    $('d6').innerHTML=weather.getElementsByTagName("time")[5].getAttribute('from').substr(11,8);
                    $('d14').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
                }
                else if(i==6){
                    $('d7').innerHTML=weather.getElementsByTagName("time")[6].getAttribute('from').substr(11,8);
                    $('d15').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
                }
                else if(i==7){
                    $('d8').innerHTML=weather.getElementsByTagName("time")[7].getAttribute('from').substr(11,8);
                    $('d16').innerHTML="<img src=\"http://openweathermap.org/img/w/"+weather_id[i]+".png\"> ";
                }
			}
			$("t0").innerHTML=weather.getElementsByTagName("time")[1].getAttribute('from').substr(0,10);
            $("weather_details").style.display="none"


        }
        else if(request.status==404)
            alert("Sorry, we can't find this city in this area!");
        else
            alert("Sorry, we have some problem in server.");
    }
}





var temp=function(){
    $("table").style.display = "none";
    $("canvas").style.display = "block";
    $("weather_details").style.display = "none";

    $("a2").style.display = "none";
    $("a1").style.display = "block";
}
var weather=function(){
    $("table").style.display = "none";
    $("a2").style.display = "block";
    $("a1").style.display = "none";
    $("canvas").style.display = "none";
    $("weather_details").style.display = "block";

}
var forecast=function(){
    $("table").style.display = "block";
    $("a2").style.display = "block";
    $("a1").style.display = "block";
    $("canvas").style.display = "none";
    $("weather_details").style.display = "none";

}


window.onload=function(){
    $("table").style.display = "none";
    $("a1").style.display="none";
    $("a2").style.display="none";
    $("weather_details").style.display = "none";

    $("city").onclick=function(){
        var city=$("cityname").value;
        console.log(city);
        var url="https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=c8bbeae86d7247afef42d90de44be0aa";
        var url_1="https://api.openweathermap.org/data/2.5/forecast?q="+city+"&mode=xml&appid=c8bbeae86d7247afef42d90de44be0aa"
        search(url);
        shift(url_1);
        forest(url_1)

    }
}