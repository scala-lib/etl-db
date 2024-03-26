import org.qrai.etldb.{database}

@main def hello(): Unit =
  val db = database.create[String]("test")
  db.commit(
    db.insert("hello")
  )
  println(db)

