var chart = null;
var chartXs = null;

$(document).ready(function(){
	var current = new Date();
	var initialYear = current.getFullYear()+3;
	for(var i =initialYear;i>=(current.getFullYear()-3);i--){
		if(i==current.getFullYear()){
			$("#years").append( $('<option></option>').val(i).html(i).prop("selected",true));
		}else{
			$("#years").append( $('<option></option>').val(i).html(i) );
		}
		 
	}
});
function loadYear(){
	var year = $("#years").val();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : context+"/yearStats-"+year,
		data : '',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			if(chart != null && chartXs != null){
				chart.destroy();
				chartXs.destroy();
			}
			$("#chartTitle").html(year);
			var labels = new Array();
			var values = new Array();
			data.forEach(function(month){
				labels.push(month.label);
				values.push(parseInt(month.value));
			});
			chart = drawBarChart(values,labels);
			chartXs = drawPieChart(values,labels);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});

}
//data [12, 19, 3, 5, 10, 3]
//labels ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"]
function drawBarChart(data,labels){
	canvas = document.getElementById("myChart");
	ctx = document.getElementById("myChart").getContext("2d");
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	var myChart = new Chart(ctx, {
	    type: 'horizontalBar',
	    data: {
	        labels: labels,
	        datasets: [{
	            label: '# of events',
	            data: data,
	            backgroundColor: 
	                'rgba(54, 162, 235, 0.2)',
	            borderColor: 
	                'rgba(54, 162, 235, 1)',
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	padding: {
                left: 0,
                right: 0,
                top: 0,
                bottom: 0
            },
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:false,
	                }
	            }],
	            xAxes: [{
	                ticks:{
	                	beginAtZero:true,
	                	stepSize: 3
	                },
	            	categoryPercentage: 1.0,
	                barPercentage: 1.0
	            }]
	        }
	    }
	});
	return myChart;	
}

function drawPieChart(data,labels){
	canvas = document.getElementById("myChart-xs");
	ctx = document.getElementById("myChart-xs").getContext("2d");
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	var myPie = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	        labels: labels,
	        datasets: [{
	            label: '# of events',
	            data: data,
	            backgroundColor:[
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
                    'rgba(50, 101, 232, 0.2)',
                    'rgba(84, 62, 235, 0.2)',
                    'rgba(255, 106, 186, 0.2)',
                    'rgba(175, 192, 192, 0.2)',
                    'rgba(253, 102, 255, 0.2)',
                    'rgba(255, 255, 64, 0.2)'
	            ],
	            borderColor: 
	                'rgba(54, 162, 235, 1)',
	            borderWidth: 1
	        }],
	        options:{
	        	padding: {
	                left: 0,
	                right: 0,
	                top: 0,
	                bottom: 0
	            }
	    	}
	    }
	});
	return myPie;
}

function showClients(){
	var year = $("#years").val();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : context+"/clientStats-"+year,
		data : '',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log(data);
			if(chart != null && chartXs != null){
				chart.destroy();
				chartXs.destroy();
			}
			$("#chartTitle").html("CLIENTS PERFORMANCE ON "+year);
			var labels = new Array();
			var values = new Array();
			data.forEach(function(month){
				labels.push(month.label);
				values.push(month.value);
			});
			chart = drawBarChart(values,labels);
			chartXs = drawPieChart(values,labels);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function showItems(){
	var year = $("#years").val();
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : context+"/itemStats-"+year,
		data : '',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log(data);
			if(chart != null && chartXs != null){
				chart.destroy();
				chartXs.destroy();
			}
			$("#chartTitle").html("ITEMS ON "+year);
			var labels = new Array();
			var values = new Array();
			data.forEach(function(month){
				labels.push(month.label);
				values.push(month.value);
			});
			chart = drawBarChart(values,labels);
			chartXs = drawPieChart(values,labels);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}