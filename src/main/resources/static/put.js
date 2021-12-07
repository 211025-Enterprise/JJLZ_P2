$(document).ready(
		function() {

				// User Details Form, adding balance
            			$("#userDetailForm").submit(function (event) {
            				// Prevent the form from submitting via the browser.
            				event.preventDefault();
							ajaxPut();
            			});

            			function ajaxPut() {

            				// PREPARE FORM DATA
            				var formData = {
            					balance: $("#balance").val()
            				}
            				// DO PUT
            				$.ajax({
            					type: "PUT",
            					contentType: "application/json",
            					url: "users/detail",
            					data: JSON.stringify(formData),
            					dataType: 'json',
            					success: function (result) {
            						if (result.status == "success") {
            							$("#postResultDiv").html(
            								" "  + "</br><strong> Your current balance:</strong><br>" +
            								result.data.balance+ "</p>");
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
		})