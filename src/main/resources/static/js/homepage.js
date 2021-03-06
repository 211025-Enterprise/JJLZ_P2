var contentWrapper = null

window.addEventListener("load", function() {
	contentWrapper = document.getElementById('content-wrapper')

	document.getElementById('create').onclick = function() {createForm()}
	document.getElementById('logIn').onclick = function() {logInForm()}
})

function createForm() {
	removeAllChildNodes(contentWrapper)
	newElement('p', 'title', contentWrapper)
	newElement('form', 'createForm', contentWrapper)
	var form = document.getElementById('createForm')
	form.setAttribute('class', 'homepageForm')
	createInput('text', 'fname', form, "First Name:")
	createInput('text', 'lname', form, "Last Name:")
	createInput('text', 'uname', form, "New Username:")
	createInput('password', 'pass', form, "New Password:")
	newElement('div', 'formControl', form)
	var formControl = document.getElementById('formControl')
	newElement('p', 'back', formControl)
	newElement('p', 'submit', formControl)
	newElement('p', 'spacer', formControl)
	document.getElementById('submit').innerHTML = "Register"
	document.getElementById('back').onclick = function() {goHome()}
	document.getElementById('submit').onclick = function() {createUser()}

	document.getElementById('title').style.marginBottom = '2rem'
	addBlurryBackground(form, contentWrapper)
}

function logInForm() {
	removeAllChildNodes(contentWrapper)
	newElement('p', 'title', contentWrapper)
	newElement('form', 'logInForm', contentWrapper)
	var form = document.getElementById('logInForm')
	form.setAttribute('class', 'homepageForm')
	createInput('text', 'username', form, "Username:")
	createInput('password', 'password', form, "Password:")
	newElement('div', 'formControl', form)
	var formControl = document.getElementById('formControl')
	newElement('p', 'back', formControl)
	newElement('p', 'submit', formControl)
	newElement('p', 'spacer', formControl)
	document.getElementById('submit').innerHTML = "Log In"
	document.getElementById('back').onclick = function() {goHome()}
	document.getElementById('submit').onclick = function() {logIn()}

	document.getElementById('title').style.marginBottom = '2rem'
	addBlurryBackground(form, contentWrapper)
}

function goHome() {
	removeAllChildNodes(contentWrapper)
	newElement('p', 'title', contentWrapper)
	newElement('p', 'create', contentWrapper)
	newElement('p', 'logIn', contentWrapper)
	document.getElementById('create').onclick = function() {createForm()}
	document.getElementById('logIn').onclick = function() {logInForm()}
}

function addBlurryBackground(formElement, parentElement) {
	var rect = formElement.getBoundingClientRect()
	var formBackground = document.createElement('div')
	formBackground.setAttribute('id', 'formBackground')
	formBackground.style.left = rect.left + 'px'
	formBackground.style.top = rect.top + 'px'
	formBackground.style.width = (rect.right - rect.left) + 'px'
	formBackground.style.height = (rect.bottom - rect.top) + 'px'
	formBackground.style.setProperty('--formPos', '-' + rect.left + 'px -' + rect.top + 'px')
	parentElement.appendChild(formBackground)
}

function createUser() {
	var formData = {
		firstName: $("#fname").val(),
		lastName: $("#lname").val(),
		username: $("#uname").val(),
		password: $("#pass").val()
	}

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "createUser",
		data: JSON.stringify(formData),
		dataType: 'json',
		success: function (result) {
			if (result.status == "success") {
				removeAllChildNodes(contentWrapper)
				newElement('p', 'title', contentWrapper)
				newElement('p', 'register-success', contentWrapper)
				newElement('p', 'back', contentWrapper)
				document.getElementById('back').onclick = function() {goHome()}
			} else {
				removeAllChildNodes(contentWrapper)
				newElement('p', 'title', contentWrapper)
				newElement('p', 'register-fail', contentWrapper)
				newElement('p', 'back', contentWrapper)
				document.getElementById('back').onclick = function() {goHome()}
			}
		},
		error: function (e) {
			removeAllChildNodes(contentWrapper)
			newElement('p', 'title', contentWrapper)
			newElement('p', 'register-fail', contentWrapper)
			newElement('p', 'back', contentWrapper)
			document.getElementById('back').onclick = function() {goHome()}
		}
	})
}

function logIn() {
	var formData = {
		username: $("#username").val(),
		password: $("#password").val()
	}

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "logged",
		data: JSON.stringify(formData),
		dataType: 'text',
		success: function (result) {
			if (result != "invalid") {
				window.localStorage.setItem("accountToken", result)
				window.location.replace("http://krakenmeister.com:8080/home")
			} else {
				removeAllChildNodes(contentWrapper)
				newElement('p', 'title', contentWrapper)
				newElement('p', 'login-fail', contentWrapper)
				newElement('p', 'back', contentWrapper)
				document.getElementById('back').onclick = function() {goHome()}
			}
		},
		error: function (e) {
			removeAllChildNodes(contentWrapper)
			newElement('p', 'title', contentWrapper)
			newElement('p', 'login-fail', contentWrapper)
			newElement('p', 'back', contentWrapper)
			document.getElementById('back').onclick = function() {goHome()}
		}
	})
}
