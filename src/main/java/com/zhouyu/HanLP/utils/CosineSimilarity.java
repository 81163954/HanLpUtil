package com.zhouyu.HanLP.utils;

import java.util.HashMap;
import java.util.Map;

public class CosineSimilarity {
// 计算余弦相似度
public static double cosineSimilarity(String text1, String text2) {
// 将文本转换为词向量
Map<String, Integer> wordMap1 = buildWordMap(text1);
Map<String, Integer> wordMap2 = buildWordMap(text2);

// 计算文本向量的模
double vector1Modulo = calculateVectorModulo(wordMap1);
double vector2Modulo = calculateVectorModulo(wordMap2);

// 计算两个文本向量的内积
double dotProduct = calculateDotProduct(wordMap1, wordMap2);

// 计算余弦相似度
double cosineSimilarity = dotProduct / (vector1Modulo * vector2Modulo);

return cosineSimilarity;
}

// 将文本转换为词向量
private static Map<String, Integer> buildWordMap(String text) {
Map<String, Integer> wordMap = new HashMap<>();

// 分词
String[] words = HanLPUtil.splitText(text);

// 统计词频
for (String word : words) {
Integer count = wordMap.get(word);
if(count == null) {
count = 0;
}
wordMap.put(word, count + 1);
}

return wordMap;
}

// 计算文本向量的模
private static double calculateVectorModulo(Map<String, Integer> wordMap) {
double vectorModulo = 0.0;

// 计算每个词的权重，将其平方并累加
for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
vectorModulo += Math.pow(entry.getValue(), 2);
}

// 取平方根
vectorModulo = Math.sqrt(vectorModulo);

return vectorModulo;
}

// 计算两个文本向量的内积
private static double calculateDotProduct(Map<String, Integer> wordMap1, Map<String, Integer> wordMap2) {
double dotProduct = 0.0;

// 遍历两个文本的词向量，计算内积
for (Map.Entry<String, Integer> entry : wordMap1.entrySet()) {
Integer count = wordMap2.get(entry.getKey());
if(count != null) {
dotProduct += entry.getValue() * count;
}
}

return dotProduct;
}

public static void main(String[] args) {
String text1 = "Java is a programming language";
String text2 = "Python is also a programming language";

double cosineSimilarity = cosineSimilarity(text1, text2);

System.out.println("余弦相似度为：" + cosineSimilarity);
}
}