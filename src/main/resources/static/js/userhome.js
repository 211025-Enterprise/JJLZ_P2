var totalValue
var fullName

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
		console.log(result)
		console.log(JSON.parse(result))
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
		console.log(result)
		console.log(JSON.parse(result))
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
