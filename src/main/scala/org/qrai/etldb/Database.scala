package org.qrai.etldb

sealed class DatabaseInstance[T](val name: String, private var rows: List[T] = List[T]()):
	def commit(db: DatabaseInstance[T]) =
		rows = db.rows

	def insert(row: T): DatabaseInstance[T] = DatabaseInstance(name, rows :+ row)
	def map(mapper: (T) => T): DatabaseInstance[T] = DatabaseInstance(name, rows.map(mapper))
	def filter(filter: (T) => Boolean): DatabaseInstance[T] = DatabaseInstance(name, rows.filter(filter))

	override def toString = s"DatabaseInstance(${rows.map(_.toString).mkString(", ")})"

object database:
	def create[T](name: String): DatabaseInstance[T] = DatabaseInstance[T](name)