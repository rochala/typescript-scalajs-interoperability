declare namespace paper {

    class PaperScope {
        readonly view: View
        setup(element: HTMLCanvasElement | string | SizeLike): void
    }

    class View {
        readonly bounds: Rectangle
        center: Point
        onFrame: Function | null
    }

    class Raster {
        position: Point
        visible: Boolean
        bounds: Rectangle

        onLoad: Function | null

        getAverageColor(object: Path | RectangleLike | PointLike): Color
        fitBounds(rectangle: RectangleLike, fill?: booresetSpirallean): void

        constructor(source?: HTMLCanvasElement | string)
    }

    class Color {
        gray: number
        constructor(r: number, g: number, b: number)
    }

    class Point {
        x: number
        y: number

        length: number
        angle: number

        rotate(angle: number, center: Point): Point

        add(value: Point | number): Point
        subtract(value: Point | number): Point
        divide(value: Point | number): Point

        constructor(x: number, y: number)

    }

    class Rectangle {
        width: number
        height: number
        constructor(x: number, y: number, width: number, y: number)
    }

    class Path {
        fillColor: String | Color

        add(value: Point): void 
        insert(index: number, value: Point): void
        smooth(): void

    }

}

declare module 'paper' {
    export = paper.PaperScope;
}
