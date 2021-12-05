export function drawLinePuzzle(LinePuzzle, service) {
    var mazeGridCanvas = document.getElementById('mazeGridCanvas');
    var startGridCanvas = document.getElementById('startGridCanvas');
    var endGridCanvas = document.getElementById('endGridCanvas');
    var pointGridCanvas = document.getElementById('pointGridCanvas');
    var deadGridCanvas = document.getElementById('deadGridCanvas');
    var zoneGridCanvas = document.getElementById('zoneGridCanvas');
    var pathGridCanvas = document.getElementById('pathGridCanvas');

    var travelGrid = LinePuzzle.mainGrid;
    var zoneGrid = LinePuzzle.innerGrid;
    var path = LinePuzzle.path;
    var size = travelGrid.vertexes.length;
    var scale;

    console.log(travelGrid); // debug

    // Draw Line Puzzle
    setScale(size);
    drawLinePuzzle();
    document.onkeydown = moveKey;

    // Move based on arrow keys press only.
    function moveKey(e) {
        e = e || window.event;
        if (e.keyCode == '38') {
            updateLinePuzzle(38) // Move Up
        }
        else if (e.keyCode == '40') {
            updateLinePuzzle(40) // Move Down
        }
        else if (e.keyCode == '37') {
            updateLinePuzzle(37) // Move Left
        }
        else if (e.keyCode == '39') {
            updateLinePuzzle(39) // Move Right
        }

    }

    function updateLinePuzzle(key) {
        service.updateCurrentGame(key).forEach(element => {
            LinePuzzle = element;
        }).then(() => {
            travelGrid = LinePuzzle.mainGrid;
            zoneGrid = LinePuzzle.innerGrid;
            path = LinePuzzle.path;
            size = travelGrid.vertexes.length;
            setScale(size);
            drawLinePuzzle();
        });
    }

    function drawLinePuzzle() {
        console.log(travelGrid); // debug
        clearCanvas();
        drawTravelGrid();
        drawPath(path);
        drawStart(travelGrid.start.x, travelGrid.start.y);
        drawEnd(travelGrid.end.x, travelGrid.end.y, "#161617");
        
        for (var x = 0; x <= travelGrid.vertexes.length - 1; x++) {
            for (var y = 0; y <= travelGrid.vertexes.length - 1; y++) {
                if (travelGrid.vertexes[x][y].required == true) drawRequiredPoints(x, y);
                if (travelGrid.vertexes[x][y].dead == true) drawDeadPoints(x, y);
            }
        }

        for (var x = 0; x <= zoneGrid.vertexes.length - 1; x++) {
            for (var y = 0; y <= zoneGrid.vertexes.length - 1; y++) {
                if (zoneGrid.vertexes[x][y].zone != 0) {
                    drawZones(x, y, zoneGrid.vertexes[x][y].zone);
                }
            }
        }
    }

    function setScale(size) {
        if (size < 3 || size > 9) return; // Unsupported LinePuzzle sizes on the Front-End.
        if (size == 3) scale = 375;
        if (size == 4) scale = 250;
        if (size == 5) scale = 187.5;
        if (size == 6) scale = 150;
        if (size == 7) scale = 125;
        if (size == 8) scale = 107.5;
        if (size == 9) scale = 94;
    }

    // Draw Line Puzzle Elements

    function drawTravelGrid() {
        if (mazeGridCanvas.getContext) {
            var mazeContext = mazeGridCanvas.getContext('2d');
            mazeContext.strokeStyle = '#161617';
            mazeContext.lineWidth = 40;
            mazeContext.beginPath();
            for (var x = 0; x < scale * size; x += scale) {
                mazeContext.moveTo(x, 0);
                mazeContext.lineTo(x, scale * size);
            }
            for (var y = 0; y < scale * size; y += scale) {
                mazeContext.moveTo(0, y);
                mazeContext.lineTo(scale * size, y);
            }
            mazeContext.closePath();
            mazeContext.stroke();
        }
    }

    function drawStart(x, y) {
        var y = size - 1 - y;
        var x_off = 90;
        var y_off = 80;
        if (startGridCanvas.getContext) {
            var startContext = startGridCanvas.getContext("2d");
            x = (x * scale) + x_off - 10;
            y = (y * scale) + y_off - 10;
            startContext.fillStyle = "orange";
            startContext.beginPath();
            startContext.arc(x, y, 40, 0, Math.PI * 2, false);
            startContext.closePath();
            startContext.fill()
        }
    }

    function drawEnd(x, y, c) {
        var y = size - 1 - y;
        var x_off = 60;
        var y_off = 80;
        if (endGridCanvas.getContext) {
            var endContext = endGridCanvas.getContext("2d");
            x = (x * scale) + x_off - 10;
            y = (y * scale) + y_off - 10;
            endContext.fillStyle = c;
            endContext.beginPath();
            endContext.arc(x, y, 40, 0, Math.PI * 2, false);
            endContext.closePath();
            endContext.fill()
        }
    }

    function drawRequiredPoints(x, y) {
        var x_off = 14;
        var y_off = 10;
        if (pointGridCanvas.getContext) {
            var pointContext = pointGridCanvas.getContext('2d');
            x = (x * scale) + x_off;
            y = (y * scale) + y_off;
            pointContext.fillStyle = "violet";
            pointContext.beginPath();
            pointContext.moveTo(x + 10, y + 0);
            pointContext.lineTo(x + 14, y + 7);
            pointContext.lineTo(x + 21, y + 7);
            pointContext.lineTo(x + 16, y + 13);
            pointContext.lineTo(x + 17, y + 20);
            pointContext.lineTo(x + 10, y + 17);
            pointContext.lineTo(x + 4, y + 20);
            pointContext.lineTo(x + 5, y + 13);
            pointContext.lineTo(x + 0, y + 7);
            pointContext.lineTo(x + 7, y + 6);
            pointContext.lineTo(x + 10, y + 0);
            pointContext.closePath();
            pointContext.fill();
        }
    }

    function drawDeadPoints(x, y) {
        var x_off = 25;
        var y_off = 19;
        if (deadGridCanvas.getContext) {
            var deadContext = deadGridCanvas.getContext('2d');
            x = (x * scale) + x_off;
            y = (y * scale) + y_off;
            deadContext.lineWidth = 3;
            deadContext.strokeStyle = "red";
            deadContext.beginPath();
            deadContext.moveTo(x - 12, y + 12);
            deadContext.lineTo(x + 12, y - 12);
            deadContext.moveTo(x - 12, y - 12);
            deadContext.lineTo(x + 12, y + 12);
            deadContext.closePath();
            deadContext.stroke();
        }
    }

    function drawZones(x, y, c) {
        var x = x + 1;
        var y = y + 1;
        var zoneColor = ["white", "black"][c - 1]
        var zoneSize;
        var zoneOffset = 1;
        if (size == 3) zoneSize = 170;
        if (size == 4) zoneSize = 110;
        if (size == 5) zoneSize = 80;
        if (size == 6) zoneSize = 60;
        if (size == 7) zoneSize = 47;
        if (size == 8) zoneSize = 38;
        if (size == 9) zoneSize = 33;

        if (zoneGridCanvas.getContext) {
            var zoneContext = zoneGridCanvas.getContext('2d');
            x = (x * scale) - ((zoneSize - 10) * zoneOffset) - zoneSize / 2;
            y = (y * scale) - ((zoneSize - 5) * zoneOffset) - zoneSize / 2;
            zoneContext.fillStyle = zoneColor;
            zoneContext.beginPath();
            zoneContext.rect(x, y, zoneSize, zoneSize);
            zoneContext.closePath();
            zoneContext.fill();
        }
    }

    function drawPath(path) {
        var x_off = 15;
        var y_off = 10;
        console.log(path)
        for (var i = 0; i < path.line.length; i++) {
            if (pathGridCanvas.getContext) {
                var pathContext = pathGridCanvas.getContext('2d');
                var x = ((path.line[i].x) * scale) + x_off;
                var y = ((size - 1 - path.line[i].y) * scale) + y_off;
                pathContext.fillStyle = "orange";
                pathContext.lineWidth = 20;
                pathContext.strokeStyle = "orange";
                pathContext.beginPath();
                pathContext.rect(x, y, 20, 20);
                if (i == 0) {
                    pathContext.moveTo(x + 10, y + 10);
                    pathContext.lineTo(((path.line[path.line.length-1].x) * scale) + x_off + 10, ((size - 1 - path.line[path.line.length-1].y) * scale) + y_off + 10);
                }
                if (i > 1) {
                    pathContext.moveTo(x + 10, y + 10);
                    pathContext.lineTo(((path.line[i-1].x) * scale) + x_off + 10, ((size - 1 - path.line[i-1].y) * scale) + y_off + 10);
                }
                pathContext.closePath();
                pathContext.stroke();
                pathContext.fill();
            }
        }
    }

    function clearCanvas() {
        mazeGridCanvas.getContext('2d').clearRect(0, 0, mazeGridCanvas.width, mazeGridCanvas.height);
        startGridCanvas.getContext('2d').clearRect(0, 0, startGridCanvas.width, startGridCanvas.height);
        endGridCanvas.getContext('2d').clearRect(0, 0, endGridCanvas.width, endGridCanvas.height);
        pointGridCanvas.getContext('2d').clearRect(0, 0, pointGridCanvas.width, pointGridCanvas.height);
        deadGridCanvas.getContext('2d').clearRect(0, 0, deadGridCanvas.width, deadGridCanvas.height);
        zoneGridCanvas.getContext('2d').clearRect(0, 0, zoneGridCanvas.width, zoneGridCanvas.height);
        pathGridCanvas.getContext('2d').clearRect(0, 0, pathGridCanvas.width, pathGridCanvas.height);
    }
}