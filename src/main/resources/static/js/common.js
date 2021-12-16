//Create a hidden form and submit it
function post(path, params, method='post') {

	const form = document.createElement('form')
	form.method = method
	form.action = path

	for (const key in params) {
		if (params.hasOwnProperty(key)) {
			const hiddenField = document.createElement('input')
			hiddenField.type = 'hidden'
			hiddenField.name = key
			hiddenField.value = params[key]
			form.appendChild(hiddenField)
		}
	}

	document.body.appendChild(form)
	form.submit()
}

function getTotalValue () {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "totalvalue",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

function getStocklist () {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "stocklist",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

function getWatchlist () {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "watchlist",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

function getStockHistory (ticker, _callback) {
	const options = {
		method: 'GET',
		url: 'https://stock-data-yahoo-finance-alternative.p.rapidapi.com/v8/finance/spark',
		params: {symbols: ticker},
		headers: {
			'x-rapidapi-host': 'stock-data-yahoo-finance-alternative.p.rapidapi.com',
			'x-rapidapi-key': '4aa500a931msh94e1a1ed0c77b04p1ff743jsn532bb2626c19'
		}
	};

	axios.request(options).then(function (response) {
		console.log(response)
		_callback(response.data)
	}).catch(function (error) {
		_callback(null)
	});
}

function buyStock (ticker, shares) {
	return $.ajax({
		type: "POST",
		data: {
			'stockname': ticker,
			'amount': shares
		},
		url: "buystock",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

function sellStock (ticker, amount) {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "sellstock",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		params: { 'stockname': ticker, 'amount': amount },
		dataType: 'text'
	})
}
