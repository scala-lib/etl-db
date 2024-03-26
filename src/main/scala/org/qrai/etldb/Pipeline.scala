package org.qrai.etldb

import scala.collection.mutable.ListBuffer

enum PipelineParallelism: case None, Default, Multithread

case class PipelineConfig(
	parallelism: PipelineParallelism = PipelineParallelism.Default
)

class Pipeline(val config: PipelineConfig):
	private var steps: ListBuffer[Tuple[Step]] = ListBuffer()

	infix def +=(step: Tuple[Step]): Unit = steps += step

	infix def ->(step: Step | List[Step | Tuple[Step]]): Unit

/*
pipeline
	-> importStep
	-> cleanStep
	-> computeStep
	-> (exportStep, reportComputeStep)
	-> reportExportStep
*/