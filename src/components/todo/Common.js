//Create a hidden form and submit it
import $ from 'jquery'; 
import axios from 'axios'
class Common{
 post(path, params, method='post') {

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

 getTotalValue () {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "totalvalue",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

 getStocklist () {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "stocklist",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

getWatchlist () {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "watchlist",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		dataType: 'text'
	})
}

 getStockHistory (ticker, _callback) {
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

buyStock (ticker, shares) {
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

sellStock (ticker, amount) {
	return $.ajax({
		type: "POST",
		contentType: "application/json",
		url: "sellstock",
		headers: { 'Authorization': window.localStorage.getItem("accountToken") },
		params: { 'stockname': ticker, 'amount': amount },
		dataType: 'text'
	})
}
}
export default new Common()