package com.grgbanking.test;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GenerateFileMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

	public void map(LongWritable ikey, Text value, Context context) 
			throws IOException, InterruptedException {
		String[] lines = value.toString().split(",");
		ImmutableBytesWritable immutableBytesWritable = new ImmutableBytesWritable(Bytes.toBytes(lines[0]));
		Put put = new Put(Bytes.toBytes(lines[0]));
		put.add(Bytes.toBytes("INFO"), Bytes.toBytes("NAME"), Bytes.toBytes(lines[1]));
		put.add(Bytes.toBytes("INFO"), Bytes.toBytes("AGE"), Bytes.toBytes(lines[2]));
		context.write(immutableBytesWritable, put);
	}

}
