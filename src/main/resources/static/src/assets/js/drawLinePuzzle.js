function drawLinePuzzle(size) {
    var size = 6;
    var scale;
    if (size == 3) scale = 375;
    if (size == 4) scale = 250;
    if (size == 5) scale = 187.5;
    if (size == 6) scale = 150;
    if (size == 7) scale = 125;
    if (size == 8) scale = 107.5;
    if (size == 9) scale = 94;
    size += 0.01

    // Draw Line Puzzle

    drawStart(size-1, 0);
    drawEnd(0, size-1);
    drawTravelGrid();
    drawRequiredPoints(size-1, size-2);
    drawRequiredPoints(size-2, size-1);
    drawZones(0, 0, 0);
    drawZones(size-2, size-2, 1);


    // Draw Line Puzzle Elements

    function drawTravelGrid() {
        var mazeGridCanvas = document.getElementById('mazeGridCanvas');
        if (mazeGridCanvas.getContext) {
            var mazeContext = mazeGridCanvas.getContext('2d');
            for (var x = 0; x < scale * size; x += scale) {
                mazeContext.moveTo(x, 0);
                mazeContext.lineTo(x, scale * size);
            }
            for (var y = 0; y < scale * size; y += scale) {
                mazeContext.moveTo(0, y);
                mazeContext.lineTo(scale * size, y);
            }

            mazeContext.strokeStyle = '#161617';
            mazeContext.lineWidth = 40;
            mazeContext.stroke();
        }
    }

    function drawStart(x, y) {
        var startGridCanvas = document.getElementById('startGridCanvas');
        var y = size - 1 - y;
        var x_off = 90;
        var y_off = 80;
        if (mazeGridCanvas.getContext) { 
            var startContext = startGridCanvas.getContext("2d");
            x = (x * scale) + x_off - 10;
            y = (y * scale) + y_off - 10;
            startContext.arc(x, y, 40, 0, Math.PI * 2, false);
            startContext.fillStyle = "#161617";
            startContext.fill()
        }
    }

    function drawEnd(x, y) {
        var endGridCanvas = document.getElementById('endGridCanvas');
        var y = size - 1 - y;
        var x_off = 60;
        var y_off = 80;
        if (mazeGridCanvas.getContext) { 
            var endContext = endGridCanvas.getContext("2d");
            x = (x * scale) + x_off - 10;
            y = (y * scale) + y_off - 10;
            endContext.arc(x, y, 40, 0, Math.PI * 2, false);
            endContext.fillStyle = "#161617";
            endContext.fill()
        }
    }

    function drawRequiredPoints(x, y) {
        var pointGridCanvas = document.getElementById('pointGridCanvas');
        var y = size - 1 - y;
        var x_off = 22;
        var y_off = 22;
        if (mazeGridCanvas.getContext) {
            var pointContext = pointGridCanvas.getContext('2d');
            x = (x * scale) + x_off - 10;
            y = (y * scale) + y_off - 10;
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

    function drawZones(x, y, c) {
        var zoneGridCanvas = document.getElementById('zoneGridCanvas');
        var x = x + 1;
        var y = size - 1 - y;
        var zoneColor = ["black", "white"][c]
        var zoneSize = 120 - (size * 10);
        var zoneOffset = 1;
        if (size == 3.01) zoneSize = 170;
        if (size == 4.01) zoneSize = 110;
        if (size == 5.01) zoneSize = 80;
        if (size == 6.01) zoneSize = 60;
        if (size == 7.01) zoneSize = 47;
        if (size == 8.01) zoneSize = 38;
        if (size == 9.01) zoneSize = 33;

        if (mazeGridCanvas.getContext) {
            var zoneContext = zoneGridCanvas.getContext('2d');
            x = (x * scale) - ((zoneSize - 10) * zoneOffset) - zoneSize / 2;
            y = (y * scale) - ((zoneSize - 5) * zoneOffset) - zoneSize / 2;
            zoneContext.fillStyle = zoneColor;
            zoneContext.beginPath();
            zoneContext.rect(x, y, zoneSize, zoneSize);
            zoneContext.fill();
        }
    }

    function drawPath(x, y) {
        var pathGridCanvas = document.getElementById('pathGridCanvas');
    }
}