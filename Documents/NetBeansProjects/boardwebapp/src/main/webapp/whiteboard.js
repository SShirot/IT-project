// JavaScript code for drawing on the whiteboard
const canvas = document.getElementById('whiteboard');
const context = canvas.getContext('2d');
let drawing = false;

// Event listeners to handle drawing
canvas.addEventListener('mousedown', startDrawing);
canvas.addEventListener('mouseup', stopDrawing);
canvas.addEventListener('mousemove', draw);

function startDrawing(e) {
    drawing = true;
    context.beginPath();
    context.moveTo(e.clientX - canvas.getBoundingClientRect().left, e.clientY - canvas.getBoundingClientRect().top);
}

function stopDrawing() {
    drawing = false;
    context.closePath();
}

function draw(e) {
    if (!drawing) return;
    context.lineTo(e.clientX - canvas.getBoundingClientRect().left, e.clientY - canvas.getBoundingClientRect().top);
    context.stroke();
}
