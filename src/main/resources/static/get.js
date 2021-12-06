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
										var user = " firstName: " + users.firstName+
										" lastName: " + users.lastName+
											" username: " + users.username+
											"<br>";
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