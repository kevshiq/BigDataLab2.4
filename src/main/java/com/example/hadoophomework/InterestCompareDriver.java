package com.example.hadoophomework;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InterestCompareDriver {
    
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("stopwords.path", args[1]);
        Job job1 = Job.getInstance(conf, "Word count");
        job1.setJarByClass(InterestCompareDriver.class);
        job1.setMapperClass(InterestCompareMapper.class);
        job1.setReducerClass(InterestCompareReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        if (!job1.waitForCompletion(true)) {
            System.exit(-1);
        }
    }
}