$(document).ready(
		function() {

			// SUBMIT FORM
			$("#userForm").submit(function (event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPostCreate();
			});

			function ajaxPostCreate() {

				// PREPARE FORM DATA
				var formData = {
					firstName: $("#firstName").val(),
					lastName: $("#lastName").val(),
					username: $("#username").val(),
					password: $("#password").val()
				}
				// DO POST
				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: "createUser",
					data: JSON.stringify(formData),
					dataType: 'json',
					success: function (result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
								" " + result.data.firstName +
								" Post Successfully! <br>" +
								"---> Congrats !!" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error: function (e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
			}

//**********************************************************************************************************

			// LOGIN FORM
			$("#loginForm").submit(function (event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPostLogin();
			});

			function ajaxPostLogin() {

				// PREPARE FORM DATA
				var formData = {
					username: $("#username").val(),
					password: $("#password").val()
				}
				// DO POST
				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: "logged",
					data: JSON.stringify(formData),
					dataType: 'json',
					success: function (result) {
						if (result.status == "success") {
							$("#postResultDiv").html("<strong>"+
								" " + result.data.username +
								" you logged in Successfully! <br>" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error: function (e) {
						alert("Error!")
						console.log("ERROR Invalid username or password: ", e);
					}
				});
			}
		})