$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

function ajaxUpdateResources() {
	successCallback = function(data) {
			viewUpdateResources(data);
			viewUpdateBuildings(data);
		};
	errorCallback = function(xhr, status, error) {
			if (validateAuthorization(xhr)){
				alert(JSON.parse(xhr.responseText).message);
			}
		};
	parameters = new Object();
	parameters.currentPlanetId = window.currentPlanetId;
	invokeAjax(requestOperation.UPDATE_RESOURCES, parameters, successCallback, errorCallback);  
}

function validateAuthorization(xhr) {
	var json = JSON.parse(xhr.responseText);
	if (json.code == 403) {
		alert(json.message);
		$("#game").addClass("d-none");
		$("#login").removeClass("d-none");
		return false;
	} else {
		return true;
	}
}

function invokeAjax(operation, parameters, successCallback, errorCallback) {
	$.ajax(
	{
		url: "http://localhost:8080/players/"+operation,
		data: JSON.stringify(parameters),
		method:"POST",
		contentType: "application/json",
		dataType: "json",
		success: successCallback,
		error: errorCallback,
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Authorization", "Bearer " + window.token );
		}   
	});
}

function doLogin() {
	var user = $('#login input[name=user]').val();
	var pass = $('#login input[name=password]').val();
	var cadenaAutorizacion = btoa(user + ":" + pass);
	$.ajax(
	{
		url: "http://localhost:8080/login",
		method: "POST",
		success: function(data) {
			window.token = data.token;
			window.currentPlanetId = data.currentPlanetId;
			$("#login").addClass("d-none");
			ajaxUpdateResources();
			$("#game").removeClass("d-none");
		},
		error: function(xhr, status, error) {
			alert(JSON.parse(xhr.responseText).message);
		},
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Authorization", "Basic " + cadenaAutorizacion );
		}   
	});
}


function viewUpdateResources(data) {
	Object.keys(data.planets[currentPlanetId].resourceAmount).forEach( key => {
		$('#resources .resource'+key).html(data.planets[currentPlanetId].resourceAmount[key]);
		}
	);
}

function viewUpdateBuildings(data) {
	console.log(data);
	window.buildingUtils = new BuildingUtils(data.planets[window.currentPlanetId]);
	Object.keys(buildings).forEach( key => {
			console.log(window.buildingUtils.getCurrentLevel(buildings[key]));
		}
	);
}

function openBuilding(buildingType) {
	console.log(window.buildingUtils);
	if (window.mainCurrentVisible != null) {
		window.mainCurrentVisible.addClass('d-none');
	}
	window.mainCurrentVisible = $('#buildingsHolder');
	window.mainCurrentVisible.removeClass('d-none');
	window.mainCurrentVisible.find(".image img").attr("src", "images/buildings/"+buildingType+".png");
	cost = window.buildingUtils.getNextLevelCost(buildingType);
	produces = window.buildingUtils.getNextLevelProduction(buildingType);
	multiplier = window.buildingUtils.getNextLevelMultiplier(buildingType);
	Object.keys(cost).forEach( key => {
		window.mainCurrentVisible.find(".cost .resource"+key).html(cost[key]);
		}
	);
	
	Object.keys(produces).forEach( key => {
		window.mainCurrentVisible.find(".production .resource"+key).html(produces[key]);
		}
	);
	
	Object.keys(multiplier).forEach( key => {
		window.mainCurrentVisible.find(".multiplier .resource"+key).html(multiplier[key]);
		}
	);
	
}
		
function utf8_to_b64( str ) {
  var encoded = window.btoa(unescape(encodeURIComponent( str )));
  if (encoded.length % 3 != 0 ) {
  	encoded+="=";
  }
  if (encoded.length % 3 != 0 ) {
  	encoded+="=";
  }
  return encoded;
}

function b64_to_utf8( str ) {
  return decodeURIComponent(escape(window.atob( str )));
}