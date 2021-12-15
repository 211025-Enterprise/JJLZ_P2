GET: $(document).ready(
		function() {

			// GET REQUEST
			$("#getAllWatchedStocksByUser).click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : "watchlist",
					success : function(result) {
						if (result.status == "success") {
							$('#getResultDiv ul').empty();
							$.each(result.data,
									function(i, stocks) {
										var stock = "<strong>StockName: " + stocks.stockName+"</strong>";
										$('#getResultDiv .list-group').append(
												stock)
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

		//url : "watchlist/{{$userid}}",