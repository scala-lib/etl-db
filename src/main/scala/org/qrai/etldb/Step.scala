package org.qrai.etldb

case class PipelineStep[F[_]](val f: F)