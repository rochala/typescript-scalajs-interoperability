package typescript.interop

import org.scalajs.dom
import org.scalajs.dom.HTMLCanvasElement

import scala.scalajs.js.JSConverters._

import scalajs.js


class ScalablyTypedFacade(elementID: String, imageName: String):
  import typings.paper.{mod => paperjs}

  var grow: Boolean = false

  setupCanvas(elementID)
  val raster = loadImage(imageName)
  createSpiralGrowth(raster)

  def setupCanvas(elementID: String) =
    val canvas = dom.document.createElement("canvas").asInstanceOf[HTMLCanvasElement]
    canvas.width = 500
    canvas.height = 500
    dom.document.getElementById(elementID).append(canvas)

    paperjs.setup(canvas)
    canvas

  def loadImage(imageName: String) =
    val raster = paperjs.Raster(imageName)
    raster.position = paperjs.view.center
    raster.visible = false
    raster.fitBounds(paperjs.view.bounds)
    raster

  def createSpiralGrowth(raster: paperjs.Raster): Unit =
    val max: Double = Math.min(raster.bounds.width, raster.bounds.height) * 0.45
    val start = paperjs.view.center
    var position = start
    var count: Int = 0

    val path = paperjs.Path()
    path.fillColor = paperjs.Color(255,255,255)

    def growSpiral(): Unit = {

      count += 1
      val vector = paperjs.view.center
      vector.angle = count * 5d
      vector.length = count / 100d
      val rotated = vector.rotate(90d, null)
      val color = raster.getAverageColor(position.add(vector.divide(2d)))
      val value = Option(color).map(color => (1d - color.gray) * 3.7).getOrElse(0d)

      rotated.length = Math.max(value, 0.2)

      path.add(position.add(vector).subtract(rotated))
      path.insert(0, position.add(vector).add(rotated))
      position = position.add(vector)
    }
    path.smooth()

    raster.onLoad = () => grow = true

    paperjs.view.onFrame = () =>
      if (grow) then
        if (start.subtract(position).length < max) then
          (0 until count / 36 + 1).foreach(_ => growSpiral())
        else
          grow = false
