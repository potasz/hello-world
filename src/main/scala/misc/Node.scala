package misc

import scala.collection.mutable

object Node {

  val Root = new Node(null)

  def apply(parent: Node): Node = {
    if (parent == null) Root else new Node(parent)
  }
}

class Node private (p: Node) {
  private val chs = mutable.ArrayBuffer[Node]()

  val parent: Node = p
  val level = lev
  val name: String = if (p != null) p.name + "." + p.addChild(this) else "1"

  private def addChild(n: Node): Int = {
    chs += n
    chs.size
  }

  private def lev: Int = {
    if (this.parent == null) 0 else 1 + this.parent.level
  }

  def children = chs.toList

  def visitAll(f: Node => Unit): Unit = {
    for (n <- this.children) {
      f(n)
      n.visitAll(f)
    }
  }

  def traverse(): List[Node] = {
    this.children.foldLeft(List(this)) { _ ::: _.traverse() }
  }

  def size(): Int = {
    this.children.foldLeft(this.children.size) { _ + _.size() }
  }

  def leftMost: Node = {
    if (children.isEmpty) this else children.head.leftMost
  }

  def rightMost: Node = {
    if (children.isEmpty) this else children.last.leftMost
  }

  def commParent(that: Node): Node = {
    var n1 = this
    var n2 = that
    val marked = mutable.Set[Node]()

    while (n1 != n2) {
      if (marked(n1)) return n1 else marked += n1
      if (marked(n2)) return n2 else marked += n2
      if (n1.parent != null) n1 = n1.parent
      if (n2.parent != null) n2 = n2.parent
    }

    n1
  }

  def pathToRoot(): List[Node] = {
    if (this.parent == null) List(this) else this :: this.parent.pathToRoot()
  }

  override def toString = name

}