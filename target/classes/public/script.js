var fileTxt = "";

function UserAction() {
    var jsonObj = JSON.parse(fileTxt);
    var jsonTxt = JSON.stringify(jsonObj);

    console.log("sending: " + jsonTxt);

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/c3po/intercept", true);
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState === XMLHttpRequest.DONE) {
            const status = xhttp.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                var text = xhttp.responseText;
                console.log("Received: " + text);

                displayResults(JSON.parse(text));
            } else {
                console.log("Error in POST request");
            }
        }
    };
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(jsonTxt);
}

function displayResults(results) {
    var node = document.getElementById("myResults");
    node.innerHTML = ""; // Clear previous content

    if (results.length > 0) {
        // Create a table element
        var table = document.createElement("table");
        table.classList.add("table", "table-bordered", "table-striped");

        // Create the table header
        var thead = document.createElement("thead");
        var headerRow = document.createElement("tr");
        var headers = ["Path","Cost", "Success Probability"];

        headers.forEach(function (header) {
            var th = document.createElement("th");
            th.textContent = header;
            headerRow.appendChild(th);
        });

        thead.appendChild(headerRow);
        table.appendChild(thead);

        // Create the table body
        var tbody = document.createElement("tbody");

        results.forEach(function (result) {
            var row = document.createElement("tr");

            // Color code based on success probability
            var successProbability = result.odds;
            if (successProbability > 0.5) {
                row.classList.add("table-success");
            } else {
                row.classList.add("table-danger");
            }

            // Add data to the row
            var pathCell = document.createElement("td");
            pathCell.textContent = result.path.toString();
            row.appendChild(pathCell);

            // Ajout de la colonne "Cost"
            var costCell = document.createElement("td");
            costCell.textContent = result.cost;
            row.appendChild(costCell);

            var probabilityCell = document.createElement("td");
            probabilityCell.textContent = successProbability.toFixed(2);
            row.appendChild(probabilityCell);

            tbody.appendChild(row);
        });

        table.appendChild(tbody);

        // Append the table to the results div
        node.appendChild(table);
    } else {
        // If no results, display a message
        node.textContent = "No results available.";
    }
}

function setAction() {
    var file = document.getElementById('file').files[0];
    var reader = new FileReader();

    reader.addEventListener("load", () => {
        fileTxt = reader.result;
        UserAction();
    }, false);

    reader.readAsText(file);
}