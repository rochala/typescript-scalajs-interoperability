package typescript.interop
package facade

import org.scalajs.dom._

import scala.scalajs.js.annotation.JSImport

import scalajs.js


@JSImport("paper", JSImport.Namespace)
@js.native
object paperjs extends js.Object:
  val view: View = js.native
  def setup(element: HTMLCanvasElement | String): Unit = js.native

@js.native
trait View extends js.Object {
  var center: Point = js.native
  var bounds: Rectangle = js.native

  var onFrame: js.Function = js.native
}

@js.native
@JSImport("paper", "Raster")
class Raster extends js.Object {
  var position: Point = js.native
  var visible: Boolean = js.native
  var bounds: Rectangle = js.native

  def fitBounds(rectangle: Rectangle): Unit = js.native
  def getAverageColor(area: Point | Rectangle): Color = js.native

  var onLoad: js.Function = js.native

  def this(image: HTMLImageElement | String) = this()
}

@js.native
@JSImport("paper", "Color")
class Color(r: Double, g: Double, b: Double) extends js.Object {
  def gray: Double = js.native
}

@js.native
@JSImport("paper", "Point")
class Point() extends js.Object {
  def this(x: Double, y: Double) = this()
  var x: Double = js.native
  var y: Double = js.native

  var angle: Double = js.native
  var length: Double = js.native

  def rotate(degrees: Double, center: Point): Point = js.native

  def add(value: Point): Point = js.native
  def subtract(value: Point): Point = js.native
  def divide(value: Double): Point = js.native
}

@js.native
@JSImport("paper", "Rectangle")
class Rectangle() extends js.Object {
  def this(x1: Double, y1: Double, x2: Double, y2: Double) = this()
  var width: Double = js.native
  var height: Double = js.native
}

@js.native
@JSImport("paper", "Path")
class Path() extends js.Object {
  var fillColor: String = js.native
  var segments: List[Any] = js.native

  def add(point: Point): Unit = js.native
  def insert(index: Int, point: Point): Unit = js.native

  def smooth(): Unit = js.native

}
