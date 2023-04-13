package typescript.interop

import org.scalajs.dom

@main
def sortingComparison(): Unit =
  dom.document.querySelector("#app").innerHTML = s"""
    <div>
      <div id="canvasPlayground">
      </div>
    </div>
  """

  ScalablyTypedFacade("canvasPlayground", "scalably-typed.png")
  ManualFacade("canvasPlayground", "scala-logo.png")
