package com.spring.batch.partition;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class ColumnRangePartitioner implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize){

        int min = 1;
        int max = 1000;
        int targetSize = (max - min)/gridSize + 1;
        Map<String, ExecutionContext> result = new HashMap<>();
        System.out.println("targetSize : "+ targetSize);

        int number = 0;
        int start = min;
        int end = start + targetSize - 1;

        while (start <= max){
            ExecutionContext value = new ExecutionContext();
            result.put("partition" + number, value);

            if (end >= max){
                end = max;
            }
            value.putInt("minValue", start); //this is like telling where to start and end a single 'batch'/chunk
            value.putInt("maxValue", end);
            start += targetSize;
            end += targetSize;
            number++;
        }
         return result;
     }

}
