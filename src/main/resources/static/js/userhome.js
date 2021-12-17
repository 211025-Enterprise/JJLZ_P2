var totalValue
var fullName

getStockHistory('AMZN', (stockhistory) => {
	console.log(stockhistory)
	new Chart('stockChart', {
		type: "line",
		data: {
			labels: stockhistory['AMZN']['timestamp'],
			datasets: [{
				fill: false,
				lineTension: 0,
				backgroundColor: "rgba(0,0,255,1.0)",
				borderColor: "rgba(0,0,255,0.1)",
				data: stockhistory['AMZN']['close']
			}]
		},
		options: {
			legend: {
				display: false
			},
			scales: {
				yAxes: [{ticks: {min: 3400, max: 3500}}]
			}
		}
	})
})

$.ajax({
	type: "POST",
	contentType: "application/json",
	url: "totalvalue",
	headers: { 'Authorization': window.localStorage.getItem("accountToken") },
	dataType: 'text',
	success: function (result) {
		totalValue = result
		if (document.readyState === "complete") {
			document.getElementById('accountsummary').innerHTML = "Net Worth: $" + totalValue
		}
	},
	error: function (e) {
		logOut()
	}
})

$.ajax({
	type: "POST",
	contentType: "application/json",
	url: "fullname",
	headers: { 'Authorization': window.localStorage.getItem("accountToken") },
	dataType: 'text',
	success: function (result) {
		fullName = result
		if (document.readyState === "complete") {
			document.getElementById('settingsBtn').innerHTML = fullName
		}
	},
	error: function (e) {
		logOut()
	}
})

$.ajax({
	type: "POST",
	contentType: "application/json",
	url: "stocklist",
	headers: { 'Authorization': window.localStorage.getItem("accountToken") },
	dataType: 'text',
	success: function (result) {
		var stocklist = JSON.parse(result)
	},
	error: function (e) {
		logOut()
	}
})

$.ajax({
	type: "POST",
	contentType: "application/json",
	url: "watchlist",
	headers: { 'Authorization': window.localStorage.getItem("accountToken") },
	dataType: 'text',
	success: function (result) {
		var watchlist = JSON.parse(result)
	},
	error: function (e) {
		logOut()
	}
})

window.addEventListener("load", function() {
	document.getElementById('settingsBtn').onclick = function() {showSettings()}
	document.getElementById('logOutBtn').onclick = function() {logOut()}
	if (fullName) {
		document.getElementById('settingsBtn').innerHTML = fullName
	}
	if (totalValue) {
		document.getElementById('accountsummary').innerHTML = "Net Worth: $" + totalValue
	}
	document.getElementById('buyBtn').onclick = function () {
		buyStock('AMZN', 1);
		getTotalValue().then(function(result) {
			document.getElementById('accountsummary').innerHTML = "Net Worth: $" + result;
		})
		getStocklist().then(function(result) {
			var stocklist = JSON.parse(result)
			console.log(stocklist)
		})
	}
})

function showSettings () {
	document.getElementById("settingsDropdown").classList.toggle("show");
}

window.onclick = function (event) {
	if (!event.target.matches('.dropbtn')) {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}

function logOut () {
	window.localStorage.removeItem("accountToken")
	window.location.replace("http://krakenmeister.com:8080")
}
