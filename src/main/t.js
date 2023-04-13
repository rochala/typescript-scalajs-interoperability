var position = view.center;
let max = Math.min(raster.bounds.width, raster.bounds.height) * 0.5;
var count = 0;
var grow = false;
let path = new Path({
    fillColor: 'black',
    closed: true
});

var raster = new Raster('mona');
raster.visible = false;
raster.on('load', grow = true);

function onFrame(event) {
	if (grow) {
		if (raster.loaded && (view.center - position).length < max) {
			for (var i = 0, l = count / 36 + 1; i < l; i++) {
				growSpiral();
			}
			path.smooth();
		} else {
			grow = false;
		}
	}
}

function growSpiral() {
    count++;
    var vector = new Point({
        angle: count * 5,
        length: count / 100
    });
    var rot = vector.rotate(90);
    var color = raster.getAverageColor(position + vector / 2);
    var value = color ? (1 - color.gray) * 3.7 : 0;
    rot.length = Math.max(value, 0.2);
    path.add(position + vector - rot);
    path.insert(0, position + vector + rot);
    position += vector;
}