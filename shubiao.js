
 window.onload = function showTable() {

			var tablename = document.getElementById("table");

			var oRows = tablename.getElementsByTagName("tr");

			for (var i = 0; i < oRows.length; i++) {

				oRows[i].onmouseover = function() {

					this.style.backgroundColor = "whitesmoke";

				}

				oRows[i].onmouseout = function() {

					this.style.backgroundColor = "white";

				}

			}

		}
