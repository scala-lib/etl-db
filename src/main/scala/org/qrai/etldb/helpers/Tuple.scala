package org.qrai.etldb

type Tuple[T] = T
    | (T, T)
    | (T, T, T)
    | (T, T, T, T)
    | (T, T, T, T, T)
    | (T, T, T, T, T, T)