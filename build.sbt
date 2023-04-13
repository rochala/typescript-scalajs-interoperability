import org.scalajs.linker.interface.ModuleSplitStyle
import scala.sys.process.{ProcessLogger, Process}

lazy val `typescript-interop` = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
  .settings(
    scalaVersion := "3.2.2",

    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("typescript.interop")))
    },

    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.3.0",
    externalNpm := {
      baseDirectory.value
    },
  )
