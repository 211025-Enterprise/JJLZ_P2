GET: $(document).ready(
		function() {

			// GET REQUEST
			$("#getALlUsers").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : "users",
					success : function(result) {
						if (result.status == "success") {
							$('#getResultDiv ul').empty();
							$.each(result.data,
									function(i, users) {
										var user = "<strong>FirstName: " + users.firstName+
										"<br> LastName: " + users.lastName+
											"<br> Balance: " + users.balance+"</strong>"
										+"<br>";
										$('#getResultDiv .list-group').append(
												user)
									});
							console.log("Success: ", result);
						} else {
							$("#getResultDiv").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
		})