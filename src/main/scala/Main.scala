import org.qrai.etldb._

@main def hello(): Unit =
  implicit val pipeline = Pipeline(parallelism = true)

  val importStep = etldb.steps.FileImport(
    file = "./data/example.csv",
    importer = etldb.importers.CSVImporter(
      delimiter = ",",
      columns = false
    )
  )

  val processStep = etldb.steps.Transform {
    case (df) => df
      .renameColumn("_1", "title")
      .renameColumn("_2", "amount")
      .renameColumn("_3", "price")
      .mapColumn("amount", _.toInt)
      .mapColumn("price", _.toInt)
      .mapColumn("price", price => price * 96) // RUB -> USD
  }

  val exportStep = etldb.steps.SQLExport(
    table = "data"
    serializer = etldb.serializers.SQLSerializer()
    connection = etldb.connections.SQLiteConnection(
      file = "./data/result.sqlite"
    )
  )

  val reportStep = etldb.steps.EmailReport(
    email = "test@gmail.com"
  )

  importStep
    -> processStep
    -> (exportStep, reportStep)
  