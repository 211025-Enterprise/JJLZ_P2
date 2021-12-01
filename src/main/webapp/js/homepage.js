document.getElementById('create').onclick = function() {createForm()}
document.getElementById('logIn').onclick = function() {logInForm()}

var contentWrapper = document.getElementById('content-wrapper')

function createForm() {
	removeAllChildNodes(contentWrapper)
}

function logInForm() {
	removeAllChildNodes(contentWrapper)
	newElement('p', 'title', contentWrapper)
	newElement('form', 'logInForm', contentWrapper)
	var form = document.getElementById('logInForm')
	createInput('text', 'username', form, "Username:")
	createInput('password', 'password', form, "Password:")
	newElement('p', 'submit', form)
	document.getElementById('submit').onclick = function() {logIn()}
}

function goHome() {
	removeAllChildNodes(contentWrapper)
	newElement('p', 'title', contentWrapper)
	newElement('p', 'create', contentWrapper)
	newElement('p', 'logIn', contentWrapper)
	document.getElementById('create').onclick = function() {createForm()}
	document.getElementById('logIn').onclick = function() {logInForm()}
}

function removeAllChildNodes(parent) {
	while (parent.firstChild) {
		parent.removeChild(parent.firstChild)
	}
}

function newElement(type, id, parent) {
	var element = document.createElement(type)
	element.setAttribute('id', id)
	parent.appendChild(element)
}

function createInput(type, id, formElement, labelString) {
	var textWrapper = document.createElement('span')
	textWrapper.setAttribute('class', 'inputWrapper')
	var textField = document.createElement('input')
	textField.setAttribute('type', type)
	textField.setAttribute('id', id)
	textField.setAttribute('name', id)
	var textLabel = document.createElement('label')
	textLabel.setAttribute('for', id)
	textLabel.innerHTML = labelString
	textWrapper.appendChild(textLabel)
	textWrapper.appendChild(textField)
	formElement.appendChild(textWrapper)
}

function logIn() {

}
