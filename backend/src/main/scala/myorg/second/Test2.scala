package myorg.second

import scala.concurrent.Future

trait Test2 {

  def method1(): Future[String]

  def method2(): Future[String]
}

class Test2Impl() extends Test2 {

  def method1(): Future[String] = {
    null
  }

  def method2(): Future[String] = {
    null
  }
}
