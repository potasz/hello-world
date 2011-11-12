package misc

import scala.collection.mutable
import misc.Node.Root

object SimpleGraph {

  def main(args: Array[String]): Unit = {

    //        val n1 = Node(Node.Root)
    //        val n2 = Node(Node.Root)
    //        val n3 = Node(n2)
    //        val n4 = Node(n2)

    genGraph(1000, 100)

    println(Root.size())

    println(Root.leftMost)
    println(Root.rightMost)
    println(Root.leftMost.commParent(Root.rightMost))

    var deepest = Root
    Root.visitAll { node =>
      if (node.level > deepest.level) deepest = node
    }

    println(deepest.level)
  }

  def genGraph(level: Int, npl: Int) {

    var parents = mutable.ArrayBuffer(Root)

    for (i <- 1 to level) {
      val num = (Math.random * npl).toInt + 1
      val nodes = mutable.ArrayBuffer[Node]()
      for (j <- 0 to num) {
        nodes += Node(parents((Math.random * parents.size).toInt))
      }
      parents = nodes
    }
  }

}