package myorg.first

import buildinfo.BuildInfo

import scala.concurrent.Future

trait TestOne {

  def method1(): Future[String]

  def method2(): Future[String]
}

class TestOneImpl() extends TestOne {

  def method1(): Future[String] = {
    BuildInfo.version
    null
  }

  def method2(): Future[String] = {
    null
  }
}
