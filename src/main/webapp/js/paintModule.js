var paintModule = angular.module('paintModule', []);


paintModule.controller('paintController', [
    function makeGrid() {

        var mazeHeight = 8;
        var mazeWidth = mazeHeight;

        var rowIndex, colIndex;

        var table = document.createElement("table");
        var tbody = document.createElement("tbody");

        for (rowIndex = 1; rowIndex <= mazeHeight; rowIndex++) {

            var row = document.createElement("tr");

            for (colIndex = 1; colIndex <= mazeWidth; colIndex++) {

                var col = document.createElement("td");
                if (rowIndex == 1 && colIndex == 1) {

                    col.style.backgroundColor = "red";
                    col.setAttribute("type", "finsih");

                } else if (rowIndex == mazeHeight && colIndex == mazeWidth) {

                    col.style.backgroundColor = "limegreen";
                    col.setAttribute("type", "start");

               

                }
                col.setAttribute("id", "cell_" + rowIndex + "_" + colIndex);

                row.appendChild(col);

            }

            tbody.appendChild(row);

        }

        table.appendChild(tbody);

        document.getElementById("grid_container").appendChild(table);
     
    }

]);