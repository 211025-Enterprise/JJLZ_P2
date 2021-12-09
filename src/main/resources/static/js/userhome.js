window.addEventListener("load", function() {
	document.getElementById('settingsBtn').onclick = function() {showSettings()}
	var totalValue = -1;
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "totalvalue",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text',
		success: function (result) {
			totalValue = result
			console.log(totalValue)
                },
                error: function (e) {
                }
        })
})

function showSettings() {
	document.getElementById("settingsDropdown").classList.toggle("show");
}

window.onclick = function(event) {
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
